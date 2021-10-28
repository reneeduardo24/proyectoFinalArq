/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import timbiriche.Cliente;
import timbiriche.Jugador;

/**
 *
 * @author rene_
 */
public class daoPreparadoSalaDeEspera {
    
    private final ConexionSala conexionSala = ConexionSala.getConexion();

    public void setPreparado(Cliente jugador, int puerto) {
        ObjectOutputStream ous = null;
        try {
            ((Jugador) jugador).setPreparado(true);
            ous = new ObjectOutputStream(conexionSala.conectar(puerto).getOutputStream());
            ous.writeObject(jugador);
            ous.close();
            conexionSala.cerrarConexion();
        } catch (IOException ex) {
            Logger.getLogger(daoPreparadoSalaDeEspera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
