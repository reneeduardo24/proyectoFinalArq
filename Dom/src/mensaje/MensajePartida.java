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
public abstract class MensajePartida implements Serializable{
    
    public abstract void accept(PartidaVisitador visitador);
}
