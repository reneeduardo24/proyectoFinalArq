/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import timbiriche.Cliente;
import timbiriche.IObservador;

/**
 *
 * @author rene_
 */
public class SeleccionPartida implements Runnable{
    
    private ServerSocket server;
    private List<String> listaDePartidas;
    private List<IObservador> listaObservadores;
    
    public SeleccionPartida() {
        
    }
    
    public SeleccionPartida(Cliente jugador, List<String> listaDePartidas) throws IOException {
        this.listaDePartidas = listaDePartidas;
        this.listaObservadores = new ArrayList<>();
        this.server = new ServerSocket(0);
        jugador.setPuertoCliente(this.server.getLocalPort());
    }

    public void agregarObservador(IObservador observador) {
        this.listaObservadores.add(observador);
    }

    private void notificarObservadores() {
        for (IObservador observadorIterador : this.listaObservadores) {
            observadorIterador.actualizar();
        }
    }
    
    @Override
    public void run() {
        try {
            Socket socketCliente;
            while (true) {
                socketCliente = this.server.accept();
                ObjectInputStream mensaje = new ObjectInputStream(socketCliente.getInputStream());
                this.listaDePartidas.clear();
                this.listaDePartidas.addAll((List<String>) mensaje.readObject());
                mensaje.close();
                socketCliente.close();
                this.notificarObservadores();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
