/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;

import java.util.Stack;

/**
 *
 * @author rene_
 */
public class TerminalExpression_Numero implements Expression {
    private int num;
    
    public TerminalExpression_Numero(int num) {
        this.num = num;
    }
    
    @Override
    public void interpret(Linea l) {
        if(l.getEjeXPuntoInicial() == -1)
            l.setEjeXPuntoInicial(num);
        
        else if(l.getEjeYPuntoInicial() == -1)
            l.setEjeYPuntoInicial(num);
        
        else if(l.getEjeXPuntoFinal() == -1)
            l.setEjeXPuntoFinal(num);
        
        else if(l.getEjeYPuntoFinal() == -1)
            l.setEjeYPuntoFinal(num);
    }
}
