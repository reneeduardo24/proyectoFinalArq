/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import datos.IpServidor.*;

/**
 *
 * @author rene_
 */
public class ConexionSala {
    
    //private String ipSala = "192.168.1.68";
    private static ConexionSala conexionSala;
    private Socket conexion;
    
    private ConexionSala() {
        
    }
    
    public static ConexionSala getConexion() {
        if (conexionSala == null) {
            conexionSala = new ConexionSala();
        }
        return conexionSala;
    }
    
    public Socket conectar(int puerto) {
        conexion = null;
        try {
            conexion = new Socket(IpServidor.ipServidor, puerto);
        } catch (IOException ex) {
            System.out.println("Error al conectar");
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
    
    public void cerrarConexion() {
        if(this.conexion != null) {
            if(!conexion.isClosed()) {
                try {
                    conexion.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar conexion");
                    Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
