package Model;
import java.io.Serializable;
import java.util.ArrayList;
 
public class EmpresaAgricola implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private String id;
    private String nombre;
    private String ubicacion;
    private String aniofundacion;
    private Usuario usuario;
    private ArrayList<Fertilizante> fertilizante;
 
    public EmpresaAgricola() {
        this.fertilizante = new ArrayList<>();
    }
 
    public EmpresaAgricola(String nombre, String ubicacion, String aniofundacion, Usuario usuario) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.aniofundacion = aniofundacion;
        this.usuario = usuario;
        this.fertilizante = new ArrayList<>();
    }
 
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
 
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
 
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
 
    public String getAniofundacion() { return aniofundacion; }
    public void setAniofundacion(String aniofundacion) { this.aniofundacion = aniofundacion; }
 
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
 
    public ArrayList<Fertilizante> getFertilizante() { return fertilizante; }
    public void setFertilizante(ArrayList<Fertilizante> fertilizante) { this.fertilizante = fertilizante; }
 
    @Override
    public String toString() {
        return "EmpresaAgricola{id='" + id + "', nombre='" + nombre + "', ubicacion='" + ubicacion +
               "', aniofundacion='" + aniofundacion + "', usuario=" + usuario +
               ", fertilizantes=" + fertilizante + "}";
    }
}