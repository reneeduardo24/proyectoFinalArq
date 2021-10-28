/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.List;
import timbiriche.Cliente;
import timbiriche.Linea;

/**
 *
 * @author rene_
 */
public class FacadadeDatos implements IFacadadeDatos {
    
    private daoRegistroJugador daoRegistroJugador;
    private daoPartida daoPartida;
    private daoUnirseAPartida daoUnirseAPartida;
    private daoPreparadoSalaDeEspera daoPreparadoSalaDeEspera;
    private daoMovimiento daoMovimiento;
    
    public FacadadeDatos() {
        this.daoRegistroJugador = new daoRegistroJugador();
        this.daoPartida = new daoPartida();
        this.daoUnirseAPartida = new daoUnirseAPartida();
        this.daoPreparadoSalaDeEspera = new daoPreparadoSalaDeEspera();
        this.daoMovimiento = new daoMovimiento();
    }

    @Override
    public void guardarJugador(Cliente jugador) {
        daoRegistroJugador.guardarJugador(jugador);
    }

    @Override
    public void registrarPartida(Cliente jugador, int capacidad) {
        this.daoPartida.registrarPartida(jugador, capacidad);
    }

    @Override
    public void unirseAPartida(Cliente jugador, String ip) {
        this.daoUnirseAPartida.unirseAPartida(jugador, ip);
    }

    @Override
    public void setPreparado(Cliente jugador, int puerto) {
        this.daoPreparadoSalaDeEspera.setPreparado(jugador, puerto);
    }
    
    @Override
    public void obtenerPartidas(Cliente jugador, String ip) {
        this.daoPartida.obtenerPartidas(jugador, ip);
    }

    @Override
    public void enviarMovimiento(List<Cliente> listaJugadores, Cliente jugador, Linea linea) {
        this.daoMovimiento.enviarMovimiento(listaJugadores, jugador, linea);
    }

    @Override
    public void abandonarPartida(List<Cliente> listaJugadores, Cliente jugador) {
        this.daoPartida.abandonarPartida(listaJugadores, jugador);
    }
    
    
}
