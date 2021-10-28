/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;



/**
 *
 * @author rene_
 */
public class TerminalExpression_Color implements Expression {
    private String color;
    
    public TerminalExpression_Color(String color) {
        this.color = color;
    }
    
    @Override
    public void interpret(Linea s) {
        s.setColor(ColorJugador.valueOf(color).getColor());
    }
}
