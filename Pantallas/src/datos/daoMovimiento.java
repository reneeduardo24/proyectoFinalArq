/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensaje.MensajeMovimiento;
import mensaje.MensajePartida;
import timbiriche.Cliente;
import timbiriche.Jugador;
import timbiriche.Linea;

/**
 *
 * @author rene_
 */
public class daoMovimiento {
    //private final ConexionServidor conexionServidor = ConexionServidor.getConexion();
    
    public void enviarMovimiento(List<Cliente> listaJugadores, Cliente jugador, Linea linea) {
        MensajePartida mensaje = new MensajeMovimiento(linea);
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
                    System.out.println("Dibujando a "+iteradorJugador.getIpCliente()+":"+iteradorJugador.getPuertoCliente()+" desde "+jugador.getIpCliente()+":"+jugador.getPuertoCliente());
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
