package Model;

import java.io.Serializable;

public class Usuario implements Serializable {
   private static final long serialVersionUID = 1L;
 
    private String id;
    private String correo;
    private String contrasena;
    private String nombre;
    private int numDocumento;
 
    public Usuario() {}
 
    public Usuario(String correo, String contrasena, String nombre, int numDocumento) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.numDocumento = numDocumento;
    }
 
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
 
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
 
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
 
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
 
    public int getNumDocumento() { return numDocumento; }
    public void setNumDocumento(int numDocumento) { this.numDocumento = numDocumento; }
 
    @Override
    public String toString() {
        return "Usuario{id='" + id + "', nombre='" + nombre + "', correo='" + correo + "', numDocumento=" + numDocumento + "}";
    }
}