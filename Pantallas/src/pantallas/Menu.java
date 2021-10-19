/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rene_
 */
public class Menu {
    Map<String, Command> menuItems = new HashMap<>();
    
    public void setCommand(String operacion, Command cmd) {
        menuItems.put(operacion, cmd);
    }
    
    public void runCommand(String operacion) {
        if(menuItems.get(operacion)!=null) {
            menuItems.get(operacion).execute();
        }
        else {
            System.out.println("Comando no encontrado: " + operacion);
        }
    }
}
