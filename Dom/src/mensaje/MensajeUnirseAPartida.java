/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensaje;

import timbiriche.Jugador;

/**
 *
 * @author rene_
 */
public class MensajeUnirseAPartida extends MensajeServidor{
    
    private String ip;
    private Jugador jugador;
    
    public MensajeUnirseAPartida(String tipoDeMensaje) {
        super(tipoDeMensaje);
    }
    
    public MensajeUnirseAPartida(String tipoDeMensaje, String ipCreador, Jugador jugador) {
        super(tipoDeMensaje);
        this.ip = ipCreador;
        this.jugador = jugador;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    @Override
    public void accept(ServidorVisitador mensajeVisitador) {
        mensajeVisitador.visitarMensajeUnirseAPartida(this);
    }
    
}
