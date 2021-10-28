/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import mensaje.MensajeServidor;
import mensaje.MensajeCrearSala;
import mensaje.MensajeRegistro;
import mensaje.MensajeSeleccionPartida;
import mensaje.MensajeUnirseAPartida;
import mensaje.ServidorVisitador;
import timbiriche.Cliente;

/**
 *
 * @author pc
 */
public class Server extends ServidorVisitador{
    private final int puerto = 2027;
    private List<Cliente> jugadores = new ArrayList<Cliente>();
    private Socket cliente;
    private ServerSocket servidor;
    
    public Server() {
        
    }
    
    public void escuchar() {
        try {
            MensajeServidor mensajeRecibido;
            //Creamos el socket servidor
            servidor = new ServerSocket(puerto);
            //Ciclo infinito para estar escuchando por nuevos jugadores
            System.out.println("Esperando jugadores....");
            while (true) {
                cliente = servidor.accept();
                System.out.println("Socket Conectado");
                ObjectInputStream mensaje = new ObjectInputStream(cliente.getInputStream());
                mensajeRecibido = (MensajeServidor) mensaje.readObject();
                mensajeRecibido.accept(this);
                mensaje.close();
                cliente.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    @Override
    public void visitarMensajeRegistro(MensajeRegistro MensajeRegistro) {
        System.out.println("Visitar mensaje registro");
        jugadores.add(MensajeRegistro.getJugador());
    }

    @Override
    public void visitarMensajeCrearSala(MensajeCrearSala MensajeCrearPartida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitarMensajeSeleccionPartida(MensajeSeleccionPartida mensajeSeleccionPartida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitarMensajeUnirseAPartida(MensajeUnirseAPartida MensajeUnirseAPartida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
    
    
}
