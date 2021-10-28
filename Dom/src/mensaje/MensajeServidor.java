/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensaje;

import java.io.Serializable;

/**
 *
 * @author rene_
 */
public abstract class MensajeServidor implements Serializable{
    
    protected String tipoDeMensaje;

    public MensajeServidor(String tipoDeMensaje) {
        this.tipoDeMensaje = tipoDeMensaje;
    }

    public String getTipoDeMensaje() {
        return tipoDeMensaje;
    }

    public void setTipoDeMensaje(String tipoDeMensaje) {
        this.tipoDeMensaje = tipoDeMensaje;
    }
    
    public abstract void accept(ServidorVisitador mensajeVisitador);
}
