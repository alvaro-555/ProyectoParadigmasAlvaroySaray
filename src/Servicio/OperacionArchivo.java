package Servicio;

import java.util.ArrayList;

import Model.EmpresaAgricola;

public interface OperacionArchivo {
     
    String Serializar(ArrayList<EmpresaAgricola> empresaAgricola, String path, String name);
 
    ArrayList<EmpresaAgricola> deserializacion(String path, String name);
}