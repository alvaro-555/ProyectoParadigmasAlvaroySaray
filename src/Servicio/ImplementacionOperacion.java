package Servicio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.EmpresaAgricola;
import Model.Fertilizante;

public class ImplementacionOperacion implements OperacionArchivo, OperacionCrud,Validacion {
private ArrayList<EmpresaAgricola> arreglo;
 
    public ImplementacionOperacion() {
        this.arreglo = new ArrayList<>();
    }
 
    public ImplementacionOperacion(ArrayList<EmpresaAgricola> arreglo) {
        this.arreglo = arreglo;
    }
 
    public ArrayList<EmpresaAgricola> getArreglo() { return arreglo; }
    public void setArreglo(ArrayList<EmpresaAgricola> arreglo) { this.arreglo = arreglo; }
 
    @Override
    public String crear(EmpresaAgricola a, Fertilizante f) {
        try {
            a.getFertilizante().add(f);
            return "Fertilizante '" + f.getNombre() + "' agregado correctamente.";
        } catch (Exception e) {
            return "Error al agregar fertilizante: " + e.getMessage();
        }
    }
 
    @Override
    public ArrayList<Fertilizante> listarTodos(EmpresaAgricola a) {
        return a.getFertilizante();
    }
 
    @Override
    public Fertilizante listarUno(EmpresaAgricola a, String id) {
        for (Fertilizante f : a.getFertilizante()) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }
 
    @Override
    public String modificar(EmpresaAgricola a, String id, Fertilizante f) {
        ArrayList<Fertilizante> lista = a.getFertilizante();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(id)) {
                lista.set(i, f);
                return "Fertilizante con id '" + id + "' modificado correctamente.";
            }
        }
        return "No se encontro el fertilizante con id '" + id + "'.";
    }
 
    @Override
    public String eliminar(EmpresaAgricola a, String id) {
        ArrayList<Fertilizante> lista = a.getFertilizante();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(id)) {
                lista.remove(i);
                return "Fertilizante con id '" + id + "' eliminado correctamente.";
            }
        }
        return "No se encontro el fertilizante con id '" + id + "'.";
    }
 
    @Override
    public String Serializar(ArrayList<EmpresaAgricola> empresaAgricola, String path, String name) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "/" + name))) {
            oos.writeObject(empresaAgricola);
            return "Datos guardados correctamente en " + name;
        } catch (IOException e) {
            return "Error al guardar: " + e.getMessage();
        }
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<EmpresaAgricola> deserializacion(String path, String name) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "/" + name))) {
            return (ArrayList<EmpresaAgricola>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
 
 
    @Override
    public boolean validarTexto(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }
 
    @Override
    public boolean validarNumeroEntero(String valor) {
        try {
            Integer.parseInt(valor.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
 
    @Override
    public boolean validarCorreo(String valor) {
        return valor != null && valor.contains("@") && valor.contains(".");
    }
 
    @Override
    public boolean validarContrasena(String valor) {
        return valor != null && valor.trim().length() >= 6;
    }
 
    @Override
    public boolean validarAniofundacion(String valor) {
        try {
            int anio = Integer.parseInt(valor.trim());
            return anio >= 1900 && anio <= 2025;
        } catch (NumberFormatException e) {
            return false;
        }
    }
 
    @Override
    public boolean validarCantidad(String valor) {
        try {
            int cantidad = Integer.parseInt(valor.trim());
            return cantidad > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
 
    @Override
    public boolean validarFecha(String valor) {
        if (valor == null || valor.trim().isEmpty()) return false;
        return valor.matches("\\d{2}/\\d{2}/\\d{4}");
    }
 
    @Override
    public String generarIdUsuario(ArrayList<EmpresaAgricola> arreglo) {
        return "U" + (arreglo.size() + 1);
    }
 
    @Override
    public String generarIdEmpresa(ArrayList<EmpresaAgricola> arreglo) {
        return "EA" + (arreglo.size() + 1);
    }
 
    @Override
    public String generarIdFertilizante(EmpresaAgricola a) {
        return "F" + (a.getFertilizante().size() + 1);
    }
 
    @Override
    public EmpresaAgricola iniciarSesion(ArrayList<EmpresaAgricola> arreglo, String correo, String contrasena) {
        for (EmpresaAgricola empresa : arreglo) {
            if (empresa.getUsuario().getCorreo().equals(correo) &&
                empresa.getUsuario().getContrasena().equals(contrasena)) {
                return empresa;
            }
        }
        return null;
    }
}