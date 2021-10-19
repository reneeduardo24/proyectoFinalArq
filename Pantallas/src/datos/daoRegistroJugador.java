/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import mensaje.MensajeServidor;
import mensaje.MensajeRegistro;
import timbiriche.Cliente;

/**
 *
 * @author pc
 */
public class daoRegistroJugador {
    
    private final ConexionServidor conexionServidor = ConexionServidor.getConexion();
    
    public void guardarJugador(Cliente jugador) {
        MensajeServidor mensaje = new MensajeRegistro("Registrar Jugador", jugador);
        ObjectOutputStream mensajeRegistro;
        try {
            mensajeRegistro = new ObjectOutputStream(this.conexionServidor.conectar().getOutputStream());
            mensajeRegistro.writeObject(mensaje);
            mensajeRegistro.close();
            this.conexionServidor.cerrarConexion();
        } catch (IOException ex) {
            System.out.println("Error al guardar Jugador");
        }
    }
}
