/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensaje.MensajeServidor;
import mensaje.MensajeCrearSala;
import mensaje.MensajeUnirseAPartida;
import timbiriche.Cliente;
import timbiriche.Jugador;

/**
 *
 * @author rene_
 */
public class daoUnirseAPartida {
    
    private final ConexionServidor conexionServidor = ConexionServidor.getConexion();

    public void unirseAPartida(Cliente jugador, String ip) {
        MensajeServidor mensaje = new MensajeUnirseAPartida("Unirse A Partida", ip, (Jugador) jugador);
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
}
