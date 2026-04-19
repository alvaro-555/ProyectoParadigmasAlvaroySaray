package Servicio;
import java.util.ArrayList;
import Model.EmpresaAgricola;
import Model.Fertilizante;

public interface OperacionCrud {
 
    String crear(EmpresaAgricola a, Fertilizante f);
 
    ArrayList<Fertilizante> listarTodos(EmpresaAgricola a);
 
    Fertilizante listarUno(EmpresaAgricola a, String id);
 
    String modificar(EmpresaAgricola a, String id, Fertilizante f);
 
    String eliminar(EmpresaAgricola a, String id);
}
