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
import timbiriche.Jugador;

/**
 *
 * @author rene_
 */
public class SalaDeEspera implements Runnable{
    
    private ServerSocket server;
    private List<Cliente> listaJugadores;
    private int puerto;
    private List<IObservador> listaObservadores;
    
    public SalaDeEspera() {
        
    }
    
    public SalaDeEspera(Cliente jugador, List<Cliente> listaJugadores, int puerto) throws IOException {
        this.listaJugadores = listaJugadores;
        this.puerto = puerto;
        this.listaObservadores = new ArrayList<>();
        this.server = new ServerSocket(0);
        jugador.setPuertoCliente(this.server.getLocalPort());
        Thread hilo = new Thread(this);
        hilo.start();
    }
    
    public void agregarObservador(IObservador observador) {
        this.listaObservadores.add(observador);
    }
    
    private void notificarObservadores() {
        for (IObservador observadorIterador : this.listaObservadores) {
            observadorIterador.actualizar();
        }
    }
    
    public boolean isJugadoresPreparados() {
        if (this.listaJugadores.size() == 4) {
            for (Cliente jugadorIterador : this.listaJugadores) {
                ((Jugador) jugadorIterador).setPreparado(true);
            }
            return true;
        }
        
        for (Cliente jugadorIterador: this.listaJugadores) {
            if(!((Jugador)jugadorIterador).isPreparado()) {
                return false;
            }
        }
        return true;
//        for (Cliente jugadorIterador : this.listaJugadores) {
//            if (!((Jugador) jugadorIterador).isPreparado()) {
//                return false;
//            }
//        }
////        if (this.listaJugadores.size() == 4) {
////            for (Cliente jugadorIterador : this.listaJugadores) {
////                ((Jugador) jugadorIterador).setPreparado(true);
////            }
////        }
//        return true;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    
    @Override
    public void run() {
        try {
            Socket socketCliente;
            while (true) {
                socketCliente = server.accept();
                ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
                this.listaJugadores.clear();
                this.listaJugadores.addAll((List<Cliente>) ois.readObject());
                this.puerto = ois.readInt();
                ois.close();
                socketCliente.close();
                this.notificarObservadores();
                if (this.isJugadoresPreparados()) {
                    server.close();
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
    
}
