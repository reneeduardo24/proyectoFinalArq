/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import pantallas.FrameInicio;

/**
 *
 * @author rene_
 */
public class SalirCommand implements Command {
    private FrameInicio pantalla;
    
    public SalirCommand(FrameInicio pantalla) {
        this.pantalla = pantalla;
    }
    
    @Override
    public void execute() {
        pantalla.dispose();
        System.exit(0);
    }
    
}
