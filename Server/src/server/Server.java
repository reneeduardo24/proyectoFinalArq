/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensaje.MensajeServidor;
import mensaje.MensajeCrearSala;
import mensaje.MensajeRegistro;
import mensaje.MensajeSeleccionPartida;
import mensaje.MensajeUnirseAPartida;
import mensaje.ServidorVisitador;
import timbiriche.Cliente;

/**
 *
 * @author rene_
 */
public class Server extends ServidorVisitador{
    private final int puerto = 2027;
    private List<Cliente> jugadores = new ArrayList<Cliente>();
    private List<Sala> partidas = new ArrayList<Sala>();
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
    public void visitarMensajeCrearSala(MensajeCrearSala MensajeCrearSala) {
        System.out.println("Mensaje Crear Partida");
        try {
            Sala sala = new Sala(MensajeCrearSala.getJugador(), MensajeCrearSala.getCapacidadDePartida());
            this.partidas.add(sala);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void visitarMensajeRegistro(MensajeRegistro MensajeRegistro) {
        System.out.println("Visitar mensaje registro");
        jugadores.add(MensajeRegistro.getJugador());
    }

    @Override
    public void visitarMensajeSeleccionPartida(MensajeSeleccionPartida mensajeSeleccionPartida) {
        System.out.println("Visitar seleccionar Partida");
        ObjectOutputStream oos;
        List<String> listaDePartidas;
        try {
            Socket socket = new Socket(mensajeSeleccionPartida.getJugador().getIpCliente(),mensajeSeleccionPartida.getJugador().getPuertoCliente());
            oos = new ObjectOutputStream(socket.getOutputStream());
            listaDePartidas = new ArrayList();
            for (Sala partida : this.partidas) {
                listaDePartidas.add(partida.toString());
            }
            oos.writeObject(listaDePartidas);
            oos.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void visitarMensajeUnirseAPartida(MensajeUnirseAPartida MensajeUnirseAPartida) {
        System.out.println("Unirse a partida");
        Sala partida = new Sala();
        partida.setIpCreador(MensajeUnirseAPartida.getIp());
        //partida.setPuertoCreador(MensajeUnirseAPartida.getJugador().getPuertoCliente());
        partida = this.partidas.get(partidas.indexOf(partida));
        try {
            partida.agregarJugador(MensajeUnirseAPartida.getJugador());
            if(partida.getCuposDisponibles() == 0) {
                this.partidas.remove(partida);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
