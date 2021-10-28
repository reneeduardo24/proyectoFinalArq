/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author rene_
 */
public class Nodo {
    private int ejeX;
    private int ejeY;
    private Color color;
    private Nodo nodoDerecha;
    private Nodo nodoIzquierda;
    private Nodo nodoArriba;
    private Nodo nodoAbajo;
    private Shape figura;

    public Nodo() {
        
    }

    public Nodo(int ejeX, int ejeY) {
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        this.figura = new Ellipse2D.Double(ejeX-5, ejeY-5, 10, 10);
    }

    public int getEjeX() {
        return ejeX;
    }

    public void setEjeX(int ejeX) {
        this.ejeX = ejeX;
    }

    public int getEjeY() {
        return ejeY;
    }

    public void setEjeY(int ejeY) {
        this.ejeY = ejeY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape getFigura() {
        return figura;
    }

    public void setFigura(Shape figura) {
        this.figura = figura;
    }

    public Nodo getNodoDerecha() {
        return nodoDerecha;
    }

    public void setNodoDerecha(Nodo nodoDerecha) {
        this.nodoDerecha = nodoDerecha;
    }

    public Nodo getNodoIzquierda() {
        return nodoIzquierda;
    }

    public void setNodoIzquierda(Nodo nodoIzquierda) {
        this.nodoIzquierda = nodoIzquierda;
    }

    public Nodo getNodoArriba() {
        return nodoArriba;
    }

    public void setNodoArriba(Nodo nodoArriba) {
        this.nodoArriba = nodoArriba;
    }

    public Nodo getNodoAbajo() {
        return nodoAbajo;
    }

    public void setNodoAbajo(Nodo nodoAbajo) {
        this.nodoAbajo = nodoAbajo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.ejeX;
        hash = 53 * hash + this.ejeY;
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
        final Nodo other = (Nodo) obj;
        if (this.ejeX != other.ejeX) {
            return false;
        }
        if (this.ejeY != other.ejeY) {
            return false;
        }
        return true;
    }
    
    
    
    public void pintar(Graphics2D g) {
        g.setColor(this.getColor());
        g.fill(this.getFigura());
    }
}
