/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.util.List;
import timbiriche.Cliente;
import timbiriche.Linea;

/**
 *
 * @author rene_
 */
public class FacadadeControl implements IFacadaDeNegocio {
    
    private ControlRegistro controlRegistro;
    private ControlPartida controlPartida;
    private ControlMovimiento controlMovimiento;
    
    public FacadadeControl() {
        this.controlRegistro = new ControlRegistro();
        this.controlPartida = new ControlPartida();
        this.controlMovimiento = new ControlMovimiento();
    }

    @Override
    public void guardarJugador(Cliente jugador) throws Exception{
        this.controlRegistro.guardarJugador(jugador);
    }

    @Override
    public void guardarPartida(Cliente jugador, int capacidad) {
        this.controlPartida.guardarPartida(jugador, capacidad);
    }

    @Override
    public void unirseAPartida(Cliente jugador, String ip) {
        this.controlPartida.unirseAPartida(jugador, ip);
    }

    @Override
    public void setPreparado(Cliente jugador, int puerto) {
        this.controlPartida.setPreparado(jugador, puerto);
    }
    
    @Override
    public void obtenerPartidas(Cliente jugador, String ip) {
        this.controlPartida.obtenerPartidas(jugador, ip);
    }

    @Override
    public void enviarMovimiento(List<Cliente> listaJugadores, Cliente jugador, Linea linea) {
        this.controlMovimiento.enviarMovimiento(listaJugadores, jugador, linea);
    }

    @Override
    public void abandonarPartida(List<Cliente> listaJugadores, Cliente jugador) {
        this.controlPartida.abandonarPartida(listaJugadores, jugador);
    }
    
}
