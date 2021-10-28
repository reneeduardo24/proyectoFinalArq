/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rene_
 */
public abstract class Cliente implements Serializable{
    
    protected String ipHost;
    protected int puertoHost;
    protected int puertoCliente;
    protected String ipCliente;
    
    public Cliente() {
        try {
            this.ipCliente = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public Socket getConexionServidor() throws IOException {
        Socket socket = new Socket(ipHost, puertoHost);
        return socket;
    }

    public int getPuertoHost() {
        return puertoHost;
    }

    public void setPuertoHost(int puertoHost) {
        this.puertoHost = puertoHost;
    }

    public String getIpHost() {
        return ipHost;
    }

    public void setIpHost(String ipHost) {
        this.ipHost = ipHost;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }
    
    public InetAddress getInitAddress() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

    public int getPuertoCliente() {
        return puertoCliente;
    }

    public void setPuertoCliente(int puertoCliente) {
        //System.out.println("el puerto del cliente cambio a " + puertoCliente);
        this.puertoCliente = puertoCliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.puertoCliente;
        hash = 79 * hash + Objects.hashCode(this.ipCliente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.puertoCliente != other.puertoCliente) {
            return false;
        }
        if (!Objects.equals(this.ipCliente, other.ipCliente)) {
            return false;
        }
        return true;
    }

    
}
