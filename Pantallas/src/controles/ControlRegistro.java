/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import datos.FacadadeDatos;
import datos.IFacadadeDatos;
import timbiriche.*;

/**
 *
 * @author rene_
 */
public class ControlRegistro {
    
    private IFacadadeDatos facadadeDatos;
    
    public ControlRegistro() {
        this.facadadeDatos = new FacadadeDatos();
    }
    
    public void guardarJugador(Cliente jugador) throws Exception {
        Jugador jugadorNuevo = (Jugador)jugador;
        if(jugadorNuevo.getAvatar() == null) {
            throw new Exception("Seleccione un personaje");
        }
        if(jugadorNuevo.getNombre().equals("")) {
            throw new Exception("Ingrese un nombre");
        }
        if(jugadorNuevo.getNombre().length() > 10) {
            throw new Exception("Ingrese un nombre no muy largo");
        }
        this.facadadeDatos.guardarJugador(jugador);
    }
}
