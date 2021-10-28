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
import java.awt.geom.Line2D;
import java.io.Serializable;

/**
 *
 * @author rene_
 */
public class Linea implements Serializable{
    
    private int ejeXPuntoInicial;
    private int ejeYPuntoInicial;
    private int ejeXPuntoFinal;
    private int ejeYPuntoFinal;
    private Color color;
    private Shape figura;

    public Linea() {
        
    }

    public Linea(int ejeXPuntoInicial, int ejeYPuntoInicial, int ejeXPuntoFinal, int ejeYPuntoFinal, Color color) {
        this.ejeXPuntoInicial = ejeXPuntoInicial;
        this.ejeYPuntoInicial = ejeYPuntoInicial;
        this.ejeXPuntoFinal = ejeXPuntoFinal;
        this.ejeYPuntoFinal = ejeYPuntoFinal;
        this.color = color;
        this.figura = new Line2D.Double(ejeXPuntoInicial, ejeYPuntoInicial, ejeXPuntoFinal, ejeYPuntoFinal);
    }

    public int getEjeXPuntoInicial() {
        return ejeXPuntoInicial;
    }

    public void setEjeXPuntoInicial(int ejeXPuntoInicial) {
        this.ejeXPuntoInicial = ejeXPuntoInicial;
    }

    public int getEjeYPuntoInicial() {
        return ejeYPuntoInicial;
    }

    public void setEjeYPuntoInicial(int ejeYPuntoInicial) {
        this.ejeYPuntoInicial = ejeYPuntoInicial;
    }

    public int getEjeXPuntoFinal() {
        return ejeXPuntoFinal;
    }

    public void setEjeXPuntoFinal(int ejeXPuntoFinal) {
        this.ejeXPuntoFinal = ejeXPuntoFinal;
    }

    public int getEjeYPuntoFinal() {
        return ejeYPuntoFinal;
    }

    public void setEjeYPuntoFinal(int ejeYPuntoFinal) {
        this.ejeYPuntoFinal = ejeYPuntoFinal;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.ejeXPuntoInicial;
        hash = 97 * hash + this.ejeYPuntoInicial;
        hash = 97 * hash + this.ejeXPuntoFinal;
        hash = 97 * hash + this.ejeYPuntoFinal;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Linea other = (Linea) obj;
        
        if(((Line2D)figura).getP1().equals(((Line2D)other.getFigura()).getP1()) && ((Line2D)figura).getP2().equals(((Line2D)other.getFigura()).getP2())) {
            return true;
        }
        if(((Line2D)figura).getP2().equals(((Line2D)other.getFigura()).getP1()) && ((Line2D)figura).getP1().equals(((Line2D)other.getFigura()).getP2())) {
            return true;
        }
        
        return false;
    }
    
    public void pintar(Graphics2D g) {
        g.setStroke(new BasicStroke(5));
        g.setColor(color);
        g.draw(figura);
    }

}
