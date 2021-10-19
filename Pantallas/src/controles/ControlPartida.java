/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import datos.FacadadeDatos;
import datos.IFacadadeDatos;
import java.util.List;
import timbiriche.Cliente;

/**
 *
 * @author rene_
 */
public class ControlPartida {
    private IFacadadeDatos facadadeDatos;
    
    public ControlPartida() {
        this.facadadeDatos = new FacadadeDatos();
    }
    
    public void guardarPartida(Cliente jugador, int capacidad) {
        this.facadadeDatos.registrarPartida(jugador, capacidad);
    }
    
    public void unirseAPartida(Cliente jugador, String ip) {
        this.facadadeDatos.unirseAPartida(jugador, ip);
    }
    
    public void setPreparado(Cliente jugador, int puerto) {
        this.facadadeDatos.setPreparado(jugador, puerto);
    }
    
    public void obtenerPartidas(Cliente jugador, String ip) {
        this.facadadeDatos.obtenerPartidas(jugador, ip);
    }
    
    public void abandonarPartida(List<Cliente> listaJugadores, Cliente jugador) {
        this.facadadeDatos.abandonarPartida(listaJugadores, jugador);
    }
}
