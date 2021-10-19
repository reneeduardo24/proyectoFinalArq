
package timbiriche;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.Icon;

/**
 *
 * @author pc
 */
public class Jugador extends Cliente implements Serializable{
    
    private String nombre;
    private Color color;
    private boolean preparado;
    private Avatar avatar;

    public Jugador(){
        
    }

    public Jugador(String nombre, Icon icono) throws IOException {
        super();
        this.nombre = nombre;
        this.avatar = new Avatar(icono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isPreparado() {
        return preparado;
    }

    public void setPreparado(boolean preparado) {
        this.preparado = preparado;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}
