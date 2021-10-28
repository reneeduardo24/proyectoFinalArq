/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author rene_
 */
public class Cuadro {
    
    private Shape figura;
    private Color color;

    public Cuadro() {
        
    }
    
    public Cuadro(Color color, double ejeX, double ejeY, double medida) {
        this.figura = new Rectangle2D.Double(ejeX, ejeY, medida, medida);
        this.color = color;
    }

    public Shape getFigura() {
        return figura;
    }

    public void setFigura(Shape figura) {
        this.figura = figura;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
        
    public void pintar(Graphics2D g) {
        g.setColor(this.getColor());
        g.fill(figura);
    }
    
}
