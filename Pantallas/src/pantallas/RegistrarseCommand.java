/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

/**
 *
 * @author rene_
 */
public class RegistrarseCommand implements Command {
    private FrameInicio pantalla;
    
    public RegistrarseCommand(FrameInicio pantalla) {
        this.pantalla = pantalla;
    }
    
    @Override
    public void execute() {
        FrameRegistro pantallaRegistroJugador = FrameRegistro.getInstacia(pantalla, true, pantalla.obtenerJugador(), pantalla.obtenerFacadaControl());
        pantallaRegistroJugador.mostrarPantalla();
        pantalla.actualizarPantalla();
    }
    
}
