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
public abstract class ServidorVisitador {
    
    public abstract void visitarMensajeCrearSala(MensajeCrearSala MensajeCrearPartida);
    
    public abstract void visitarMensajeRegistro(MensajeRegistro MensajeRegistro);
    
    public abstract void visitarMensajeSeleccionPartida(MensajeSeleccionPartida mensajeSeleccionPartida);
    
    public abstract void visitarMensajeUnirseAPartida(MensajeUnirseAPartida MensajeUnirseAPartida);
    
    
}
