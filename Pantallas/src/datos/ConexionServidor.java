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

/**
 *
 * @author rene_
 */
public class ConexionServidor {
//    private String ipServidor = "192.168.1.68";
//    private int puertoServidor = 2027;
    private static ConexionServidor conexionServidor;
    private Socket conexion;
    
    private ConexionServidor() {
        
    }
    
    public static ConexionServidor getConexion() {
        if (conexionServidor == null) {
            conexionServidor = new ConexionServidor();
        }
        return conexionServidor;
    }
    
    public Socket conectar() {
        conexion = null;
        try {
            conexion = new Socket(IpServidor.ipServidor, IpServidor.puertoServidor);
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
