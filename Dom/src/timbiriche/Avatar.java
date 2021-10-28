/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;

import java.io.Serializable;
import javax.swing.Icon;


/**
 *
 * @author rene_
 */


public class Avatar implements Serializable{
    
    private Icon icono;

    public Avatar() {
    
    }

    public Avatar(Icon icono) {
        this.icono = icono;
    }
    
    public Icon getIcono() {
        return icono;
    }

    public void setIcono(Icon icono) {
        this.icono = icono;
    }    
}
