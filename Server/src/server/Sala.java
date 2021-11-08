/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import timbiriche.Cliente;
import timbiriche.ColorJugador;
import timbiriche.Jugador;

/**
 *
 * @author rene_
 */
public class Sala implements Runnable {
    
    private int capacidadDePartida;
    private int cuposDisponibles;
    private int puerto;
    private int puertoCreador;
    private String ipCreador;
    private ServerSocket servidor;
    //private Socket socket;
    private List<Cliente> jugadores;
    private List<ColorJugador> listaDeColores;
    
    public Sala() {
        
    }

    public Sala(Cliente jugador, int capacidadDePartida) throws IOException {
        this.servidor = new ServerSocket(0);
        this.jugadores = new ArrayList<>();
        this.puerto = servidor.getLocalPort();
        this.ipCreador = jugador.getIpCliente();
        this.capacidadDePartida = capacidadDePartida;
        this.cuposDisponibles = capacidadDePartida;
        this.listaDeColores = new ArrayList<>();
        for (ColorJugador color : ColorJugador.values()) {
            this.listaDeColores.add(color);
        }
        Thread hilo = new Thread(this);
        hilo.start();
    }

    public int getCapacidadDePartida() {
        return capacidadDePartida;
    }

    public void setCapacidadDePartida(int capacidadDePartida) {
        this.capacidadDePartida = capacidadDePartida;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public List<Cliente> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Cliente> jugadores) {
        this.jugadores = jugadores;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int ip) {
        this.puerto = ip;
    }

    public String getIpCreador() {
        return ipCreador;
    }

    public void setIpCreador(String ipCreador) {
        this.ipCreador = ipCreador;
    }

    public int getPuertoCreador() {
        return puertoCreador;
    }

    public void setPuertoCreador(int puertoCreador) {
        this.puertoCreador = puertoCreador;
    }
    
    public void agregarJugador(Cliente jugador) throws IOException {
        if (cuposDisponibles > 0) {
            ((Jugador)jugador).setColor(this.obtenerColor(jugador));
            this.jugadores.add(jugador);
            this.cuposDisponibles--;
            enviarLista();
        }
    }
    
    public Color obtenerColor(Cliente jugador) {
        int numeroAleatorio = (int)(Math.random() * this.listaDeColores.size());
        Color color = this.listaDeColores.remove(numeroAleatorio).getColor();
        return color;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.puertoCreador;
        hash = 47 * hash + Objects.hashCode(this.ipCreador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sala other = (Sala) obj;
        if (this.puertoCreador != other.puertoCreador) {
            return false;
        }
        if (!Objects.equals(this.ipCreador, other.ipCreador)) {
            return false;
        }
        return true;
    }

    
    
    public void enviarLista() throws IOException {
        Socket nuevoSocket;
        List<Cliente> lista;
//        if(this.jugadores.size() == 4) {
//            this.servidor.close();
//        }
        if (this.jugadores.size() == this.capacidadDePartida) {
            lista = this.crearTurnos();
        } else {
            lista = this.jugadores;
        }
        for (Cliente iteradorJugador : jugadores) {
            nuevoSocket = new Socket(iteradorJugador.getIpCliente(), iteradorJugador.getPuertoCliente());
            ObjectOutputStream ous = new ObjectOutputStream(nuevoSocket.getOutputStream());
            ous.writeObject(lista);
            ous.writeInt(puerto);
            ous.close();
            nuevoSocket.close();
        }
    }
    
    public List<Cliente> crearTurnos() {
        List<Cliente> listaConTurnos = new ArrayList<>();
        List<Cliente> listaSinTurnos = new ArrayList<>(this.jugadores);
        int numero;
        Jugador jugadorIterador;
        for (int i = this.jugadores.size()-1; i >= 0; i--) {
            numero = (int)(Math.random()*(i+1));
            jugadorIterador = (Jugador)listaSinTurnos.remove(numero);
            listaConTurnos.add(jugadorIterador);
        }
        return listaConTurnos;
    }

    @Override
    public String toString() {
        if(jugadores.isEmpty())
            return "";
        
        return ((Jugador)jugadores.get(0)).getNombre()+","+cuposDisponibles + "," + capacidadDePartida + "," + this.ipCreador + "," + this.getPuerto();
    }
    
    @Override
    public void run() {
        try {
            Socket socketCliente;
            while(true) {
                socketCliente = servidor.accept();
                ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
                Cliente jugador = (Jugador)ois.readObject();
                ois.close();
                socketCliente.close();
                ((Jugador)this.jugadores.get(this.jugadores.indexOf(jugador))).setPreparado(true);
                enviarLista();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
