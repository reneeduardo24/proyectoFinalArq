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
 * @author pc
 */
public interface IFacadaDeNegocio {
    
    public void guardarJugador(Cliente jugador)throws Exception;
    
    public void guardarPartida(Cliente jugador, int capacidad);
    
    public void unirseAPartida(Cliente jugador, String ip);
    
    public void setPreparado(Cliente jugador, int puerto);
    
    public void obtenerPartidas(Cliente jugador, String ip);
    
    public void enviarMovimiento(List<Cliente> listaJugadores, Cliente jugador, Linea linea);
    
    public void abandonarPartida(List<Cliente> listaJugadores, Cliente jugador);
}
