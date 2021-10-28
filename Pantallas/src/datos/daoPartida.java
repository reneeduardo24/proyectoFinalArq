/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensaje.MensajeAbandonarPartida;
import mensaje.MensajeServidor;
import mensaje.MensajeCrearSala;
import mensaje.MensajeMovimiento;
import mensaje.MensajePartida;
import mensaje.MensajeSeleccionPartida;
import timbiriche.Cliente;
import timbiriche.Jugador;

/**
 *
 * @author rene_
 */
public class daoPartida {

    private final ConexionServidor conexionServidor = ConexionServidor.getConexion();

    public void registrarPartida(Cliente jugador, int capacidad) {
        MensajeServidor mensaje = new MensajeCrearSala("Crear Partida", capacidad, (Jugador) jugador);
        ObjectOutputStream mensajeRegistro;
        try {
            mensajeRegistro = new ObjectOutputStream(conexionServidor.conectar().getOutputStream());
            mensajeRegistro.writeObject(mensaje);
            mensajeRegistro.close();
            conexionServidor.cerrarConexion();
        } catch (IOException ex) {
            Logger.getLogger(daoPartida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerPartidas(Cliente jugador, String ip) {
        MensajeServidor mensaje = new MensajeSeleccionPartida("Seleccionar Partida", jugador);
        ObjectOutputStream mensajeRegistro;
        try {
            mensajeRegistro = new ObjectOutputStream(conexionServidor.conectar().getOutputStream());
            mensajeRegistro.writeObject(mensaje);
            mensajeRegistro.close();
            conexionServidor.cerrarConexion();
        } catch (IOException ex) {
            Logger.getLogger(daoPartida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abandonarPartida(List<Cliente> listaJugadores, Cliente jugador) {
        MensajePartida mensaje = new MensajeAbandonarPartida(jugador);
        Socket cliente;
//        List datos = new ArrayList();
//        datos.add(linea.getEjeXPuntoInicial());
//        datos.add(linea.getEjeYPuntoInicial()); 
//        datos.add(linea.getEjeXPuntoFinal());
//        datos.add(linea.getEjeYPuntoFinal()); 
//        datos.add(((Jugador)jugador).getColor());
        for (Cliente iteradorJugador : listaJugadores) {
            if (!iteradorJugador.equals(jugador)) {
                try {
                    cliente = new Socket(iteradorJugador.getIpCliente(), iteradorJugador.getPuertoCliente());
                    ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
                    oos.writeObject(mensaje);
                    oos.close();
                    cliente.close();
                } catch (IOException ex) {
                    Logger.getLogger(daoMovimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }
}
