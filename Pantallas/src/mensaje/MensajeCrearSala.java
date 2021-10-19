/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensaje;

import java.io.Serializable;
import timbiriche.Cliente;
import timbiriche.Jugador;

/**
 *
 * @author rene_
 */
public class MensajeCrearSala extends MensajeServidor{

    private int capacidadDePartida;
    private Jugador jugador;
    
    public MensajeCrearSala(String tipoDeMensaje, int capacidadDePartida, Jugador jugador) {
        super(tipoDeMensaje);
        this.capacidadDePartida=capacidadDePartida;
        this.jugador = jugador;
    }

    public int getCapacidadDePartida() {
        return capacidadDePartida;
    }

    public void setCapacidadDePartida(int capacidadDePartida) {
        this.capacidadDePartida = capacidadDePartida;
    }

    public Cliente getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void accept(ServidorVisitador mensajeVisitador) {
        mensajeVisitador.visitarMensajeCrearSala(this);
    }
    
}
