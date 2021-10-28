/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensaje;

/**
 *
 * @author rene_
 */
public abstract class PartidaVisitador {
    
    public abstract void visitarMensajeAbandonarPartida(MensajeAbandonarPartida mensajeAbandonarPartida);
    
    public abstract void visitarMensajeMovimiento(MensajeMovimiento mensajeMovimeinto);

}
