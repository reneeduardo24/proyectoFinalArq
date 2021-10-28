/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 *
 * @author rene_
 */
public class Parser {
   private ArrayList<Expression> parseTree = new ArrayList<>(); 

   public Parser(String s) {
       for (String token : s.split(",")) {
            //System.out.println(token);
            if(token.matches("[A-Z]+")) {
               //System.out.println("color");
               parseTree.add(new TerminalExpression_Color(token));
            }
            else {                       
               //System.out.println("num");
               parseTree.add(new TerminalExpression_Numero(Integer.valueOf(token)));
            }
       }
   }

   public Linea evaluate() {
       Linea l = new Linea();
       l.setEjeXPuntoInicial(-1);
       l.setEjeXPuntoFinal(-1);
       l.setEjeYPuntoInicial(-1);
       l.setEjeYPuntoFinal(-1);
       
       for (Expression e : parseTree) //set ejes y color
           e.interpret(l);
       
       l.setFigura(new Line2D.Double(l.getEjeXPuntoInicial(), l.getEjeYPuntoInicial(), l.getEjeXPuntoFinal(), l.getEjeYPuntoFinal()));
       
       return l;
   }
}
