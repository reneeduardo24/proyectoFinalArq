/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensaje;

import timbiriche.ColorJugador;
import timbiriche.Linea;
import timbiriche.Parser;

/**
 *
 * @author rene_
 */
public class MensajeMovimiento extends MensajePartida {
    //private Linea linea;
    private String stringLinea;
    
    public MensajeMovimiento(Linea linea) {
        //this.linea = linea;
        this.stringLinea = linea.getEjeXPuntoInicial() + ","
                         + linea.getEjeYPuntoInicial() + ","
                         + linea.getEjeXPuntoFinal() + ","
                         + linea.getEjeYPuntoFinal() + ",";
                
        for (ColorJugador color : ColorJugador.values()) {
            if(color.getColor().equals(linea.getColor()))
                this.stringLinea += color.name();
        }
    }

    public Linea getLinea() {
        return new Parser(stringLinea).evaluate();
    }
    /*
    public void setLinea(Linea linea) {
        this.linea = linea;
    }
    */
    @Override
    public void accept(PartidaVisitador visitador) {
        visitador.visitarMensajeMovimiento(this);
    }
    
}
