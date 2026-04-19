package Model;

import java.io.Serializable;

public class Fertilizante implements Serializable {
 private static final long serialVersionUID = 1L;
 
    private String id;
    private String tipo;
    private String nombre;
    private String fechaAdquisicion;
    private int cantidad;
 
    public Fertilizante() {}
 
    public Fertilizante(String tipo, String nombre, String fechaAdquisicion, int cantidad) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.fechaAdquisicion = fechaAdquisicion;
        this.cantidad = cantidad;
    }
 
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
 
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
 
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
 
    public String getFechaAdquisicion() { return fechaAdquisicion; }
    public void setFechaAdquisicion(String fechaAdquisicion) { this.fechaAdquisicion = fechaAdquisicion; }
 
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
 
    @Override
    public String toString() {
        return "Fertilizante{id='" + id + "', nombre='" + nombre + "', tipo='" + tipo +
               "', cantidad=" + cantidad + ", fechaAdquisicion='" + fechaAdquisicion + "'}";
    }
}