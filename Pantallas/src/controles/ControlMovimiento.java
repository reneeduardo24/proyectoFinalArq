/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import datos.FacadadeDatos;
import datos.IFacadadeDatos;
import java.util.List;
import timbiriche.Cliente;
import timbiriche.Linea;

/**
 *
 * @author rene_
 */
public class ControlMovimiento {
    private IFacadadeDatos facadaDeDatos;

    public ControlMovimiento() {
        this.facadaDeDatos = new FacadadeDatos();
    }
    
    public void enviarMovimiento(List<Cliente> listaJugadores, Cliente jugador, Linea linea){
        this.facadaDeDatos.enviarMovimiento(listaJugadores, jugador, linea);
    }
}
