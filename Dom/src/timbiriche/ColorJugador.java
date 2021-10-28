/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;

import java.awt.Color;


/**
 *
 * @author rene_
 */
public enum ColorJugador {
    ROJO(new Color(224,87,87)),
    VERDE(new Color(32, 134, 0)),
    AMARILLO(new Color(236,236,102)),
    ROSA(new Color(246,150,242)),
    NARANJA(new Color(243, 156, 18)),
    MORADO(new Color(125, 60, 152)),
    AZUL(new Color(104,135,231));
    
    private Color color;
    
    private ColorJugador(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return this.color;
    }
    
}
