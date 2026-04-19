package Servicio;

import java.util.ArrayList;

import Model.EmpresaAgricola;

public interface Validacion {

    boolean validarTexto(String valor);
    boolean validarNumeroEntero(String valor);
    boolean validarCorreo(String valor);
    boolean validarContrasena(String valor);
    boolean validarAniofundacion(String valor);
 

    boolean validarCantidad(String valor);
    boolean validarFecha(String valor);
 

    String generarIdUsuario(ArrayList<EmpresaAgricola> arreglo);
    String generarIdEmpresa(ArrayList<EmpresaAgricola> arreglo);
    String generarIdFertilizante(EmpresaAgricola a);
 

    EmpresaAgricola iniciarSesion(ArrayList<EmpresaAgricola> arreglo, String correo, String contrasena);
}
