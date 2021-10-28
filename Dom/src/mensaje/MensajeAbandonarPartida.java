/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensaje;

import timbiriche.Cliente;

/**
 *
 * @author rene_
 */
public class MensajeAbandonarPartida extends MensajePartida {

    private Cliente jugador;

    public MensajeAbandonarPartida(Cliente jugador) {
        this.jugador = jugador;
    }

    public Cliente getJugador() {
        return jugador;
    }

    public void setJugador(Cliente jugador) {
        this.jugador = jugador;
    }
    
    @Override
    public void accept(PartidaVisitador visitador) {
        visitador.visitarMensajeAbandonarPartida(this);
    }

}
