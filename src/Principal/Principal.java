package Principal;
import java.util.ArrayList;
import java.util.Scanner;
import Model.Fertilizante;
import Model.EmpresaAgricola;
import Model.Usuario;
import Servicio.ImplementacionOperacion;

public class Principal {
static ImplementacionOperacion op = new ImplementacionOperacion();
    static Scanner sc = new Scanner(System.in);
    static final String PATH = "Datos";
    static final String FILE = "Informacion.dat";
 
    public static void main(String[] args) {
 
        new java.io.File(PATH).mkdirs();
        op.setArreglo(op.deserializacion(PATH, FILE));
 
        boolean salir = false;
 
        while (!salir) {
 
            System.out.println("\nBienvenido a su gestor de fertilizantes, que desea hacer?");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
 
            String opcion = sc.nextLine().trim();
 
            switch (opcion) {
 
                case "1": {
                    System.out.println("\n-- Inicio de Sesion --");
 
                    System.out.print("Correo: ");
                    String correo = sc.nextLine().trim();
 
                    if (!op.validarCorreo(correo)) {
                        System.out.println("Correo no valido. Debe contener @ y .");
                        break;
                    }
 
                    System.out.print("Contraseña: ");
                    String contrasena = sc.nextLine().trim();
 
                    if (!op.validarContrasena(contrasena)) {
                        System.out.println("La contraseña debe tener al menos 6 caracteres.");
                        break;
                    }
 
                    EmpresaAgricola empresaLogin = op.iniciarSesion(op.getArreglo(), correo, contrasena);
 
                    if (empresaLogin == null) {
                        System.out.println("Correo o contraseña incorrectos.");
                        break;
                    }
 
                    System.out.println("Bienvenido, " + empresaLogin.getUsuario().getNombre());
                    System.out.println("Empresa: " + empresaLogin.getNombre());
 
                    
                    boolean cerrarSesion = false;
 
                    while (!cerrarSesion) {
 
                        System.out.println("\n-- Menu de Fertilizantes --");
                        System.out.println("1. Agregar fertilizante");
                        System.out.println("2. Consultar fertilizantes");
                        System.out.println("3. Editar fertilizante");
                        System.out.println("4. Buscar fertilizante por ID");
                        System.out.println("5. Eliminar fertilizante");
                        System.out.println("6. Cerrar sesion");
                        System.out.print("Seleccione una opcion: ");
 
                        String opFert = sc.nextLine().trim();
 
                        switch (opFert) {
 
                            case "1": {
                                System.out.println("\n-- Agregar Fertilizante --");
 
                                System.out.print("Nombre: ");
                                String nombre = sc.nextLine().trim();
                                if (!op.validarTexto(nombre)) {
                                    System.out.println("El nombre no puede estar vacio.");
                                    break;
                                }
 
                                System.out.print("Tipo: ");
                                String tipo = sc.nextLine().trim();
                                if (!op.validarTexto(tipo)) {
                                    System.out.println("El tipo no puede estar vacio.");
                                    break;
                                }
 
                                String fecha = "";
                                boolean fechaValida = false;
                                while (!fechaValida) {
                                    System.out.print("Fecha de adquisicion (DD/MM/AAAA): ");
                                    fecha = sc.nextLine().trim();
                                    if (op.validarFecha(fecha)) {
                                        fechaValida = true;
                                    } else {
                                        System.out.println("Fecha invalida. Use el formato DD/MM/AAAA.");
                                    }
                                }
 
                                String cantidadStr = "";
                                boolean cantidadValida = false;
                                while (!cantidadValida) {
                                    System.out.print("Cantidad (numero entero mayor a 0): ");
                                    cantidadStr = sc.nextLine().trim();
                                    if (op.validarCantidad(cantidadStr)) {
                                        cantidadValida = true;
                                    } else {
                                        System.out.println("Cantidad invalida. Debe ser un numero entero mayor a 0.");
                                    }
                                }
 
                                String idFert = op.generarIdFertilizante(empresaLogin);
                                Fertilizante f = new Fertilizante(tipo, nombre, fecha, Integer.parseInt(cantidadStr));
                                f.setId(idFert);
 
                                System.out.println(op.crear(empresaLogin, f));
                                op.Serializar(op.getArreglo(), PATH, FILE);
                                break;
                            }
 
                            case "2": {
                                System.out.println("\n-- Lista de Fertilizantes --");
                                ArrayList<Fertilizante> lista = op.listarTodos(empresaLogin);
 
                                if (lista.isEmpty()) {
                                    System.out.println("No hay fertilizantes registrados.");
                                } else {
                                    for (Fertilizante f : lista) {
                                        System.out.println("ID: " + f.getId() +
                                            " | Nombre: " + f.getNombre() +
                                            " | Tipo: " + f.getTipo() +
                                            " | Cantidad: " + f.getCantidad() +
                                            " | Fecha: " + f.getFechaAdquisicion());
                                    }
                                }
                                break;
                            }
 
                            case "3": {
                                System.out.println("\n-- Editar Fertilizante --");
                                System.out.println("Recuerde que los IDs tienen el formato F+numero. Ejemplo: F1, F2.");
 
                                System.out.print("Ingrese el ID del fertilizante a editar: ");
                                String idEditar = sc.nextLine().trim();
 
                                Fertilizante existente = op.listarUno(empresaLogin, idEditar);
                                if (existente == null) {
                                    System.out.println("No se encontro un fertilizante con ID '" + idEditar + "'.");
                                    break;
                                }
 
                                System.out.println("Fertilizante encontrado: " + existente.getNombre());
                                System.out.println("Ingrese los nuevos datos:");
 
                                System.out.print("Nombre: ");
                                String nuevoNombre = sc.nextLine().trim();
                                if (!op.validarTexto(nuevoNombre)) {
                                    System.out.println("El nombre no puede estar vacio.");
                                    break;
                                }
 
                                System.out.print("Tipo: ");
                                String nuevoTipo = sc.nextLine().trim();
                                if (!op.validarTexto(nuevoTipo)) {
                                    System.out.println("El tipo no puede estar vacio.");
                                    break;
                                }
 
                                String nuevaFecha = "";
                                boolean nFechaValida = false;
                                while (!nFechaValida) {
                                    System.out.print("Fecha de adquisicion (DD/MM/AAAA): ");
                                    nuevaFecha = sc.nextLine().trim();
                                    if (op.validarFecha(nuevaFecha)) {
                                        nFechaValida = true;
                                    } else {
                                        System.out.println("Fecha invalida. Use el formato DD/MM/AAAA.");
                                    }
                                }
 
                                String nuevaCantStr = "";
                                boolean nCantValida = false;
                                while (!nCantValida) {
                                    System.out.print("Cantidad: ");
                                    nuevaCantStr = sc.nextLine().trim();
                                    if (op.validarCantidad(nuevaCantStr)) {
                                        nCantValida = true;
                                    } else {
                                        System.out.println("Cantidad invalida. Debe ser un numero entero mayor a 0.");
                                    }
                                }
 
                                Fertilizante editado = new Fertilizante(nuevoTipo, nuevoNombre, nuevaFecha, Integer.parseInt(nuevaCantStr));
                                editado.setId(idEditar);
 
                                System.out.println(op.modificar(empresaLogin, idEditar, editado));
                                op.Serializar(op.getArreglo(), PATH, FILE);
                                break;
                            }
 
                            case "4": {
                                System.out.println("\n-- Buscar Fertilizante por ID --");
                                System.out.println("Recuerde que los IDs tienen el formato F+numero. Ejemplo: F1, F2.");
 
                                System.out.print("Ingrese el ID: ");
                                String idBuscar = sc.nextLine().trim();
 
                                Fertilizante encontrado = op.listarUno(empresaLogin, idBuscar);
                                if (encontrado != null) {
                                    System.out.println("ID       : " + encontrado.getId());
                                    System.out.println("Nombre   : " + encontrado.getNombre());
                                    System.out.println("Tipo     : " + encontrado.getTipo());
                                    System.out.println("Cantidad : " + encontrado.getCantidad());
                                    System.out.println("Fecha    : " + encontrado.getFechaAdquisicion());
                                } else {
                                    System.out.println("No se encontro un fertilizante con ID '" + idBuscar + "'.");
                                }
                                break;
                            }
 
                            case "5": {
                                System.out.println("\n-- Eliminar Fertilizante --");
                                ArrayList<Fertilizante> listaElim = op.listarTodos(empresaLogin);
 
                                if (listaElim.isEmpty()) {
                                    System.out.println("No hay fertilizantes registrados.");
                                    break;
                                }
 
                                System.out.println("Fertilizantes disponibles:");
                                for (Fertilizante f : listaElim) {
                                    System.out.println("ID: " + f.getId() +
                                        " | Nombre: " + f.getNombre() +
                                        " | Tipo: " + f.getTipo() +
                                        " | Cantidad: " + f.getCantidad());
                                }
 
                                System.out.println("Recuerde que los IDs tienen el formato F+numero. Ejemplo: F1, F2.");
                                System.out.print("Ingrese el ID del fertilizante a eliminar: ");
                                String idEliminar = sc.nextLine().trim();
 
                                System.out.println(op.eliminar(empresaLogin, idEliminar));
                                op.Serializar(op.getArreglo(), PATH, FILE);
                                break;
                            }
 
                            case "6": {
                                System.out.println("Sesion cerrada. Hasta luego, " + empresaLogin.getUsuario().getNombre() + ".");
                                cerrarSesion = true;
                                break;
                            }
 
                            default: {
                                System.out.println("Opcion no valida. Intente de nuevo.");
                            }
                        }
                    }
                    break;
                }
 
                case "2": {
                    System.out.println("\n-- Registro de Empresa --");
 
                    System.out.print("Nombre de la empresa: ");
                    String nombreEmpresa = sc.nextLine().trim();
                    if (!op.validarTexto(nombreEmpresa)) {
                        System.out.println("El nombre no puede estar vacio.");
                        break;
                    }
 
                    System.out.print("Ubicacion: ");
                    String ubicacion = sc.nextLine().trim();
                    if (!op.validarTexto(ubicacion)) {
                        System.out.println("La ubicacion no puede estar vacia.");
                        break;
                    }
 
                    String anio = "";
                    boolean anioValido = false;
                    while (!anioValido) {
                        System.out.print("Año de fundacion (1900-2025): ");
                        anio = sc.nextLine().trim();
                        if (op.validarAniofundacion(anio)) {
                            anioValido = true;
                        } else {
                            System.out.println("Año invalido. Ingrese un valor entre 1900 y 2025.");
                        }
                    }
 
                    System.out.println("\n-- Datos del Usuario --");
 
                    System.out.print("Nombre: ");
                    String nombreUsuario = sc.nextLine().trim();
                    if (!op.validarTexto(nombreUsuario)) {
                        System.out.println("El nombre no puede estar vacio.");
                        break;
                    }
 
                    String correoReg = "";
                    boolean correoValido = false;
                    while (!correoValido) {
                        System.out.print("Correo electronico: ");
                        correoReg = sc.nextLine().trim();
                        if (op.validarCorreo(correoReg)) {
                            correoValido = true;
                        } else {
                            System.out.println("Correo invalido. Debe contener @ y .");
                        }
                    }
 
                    String contrasenaReg = "";
                    boolean contrasenaValida = false;
                    while (!contrasenaValida) {
                        System.out.print("Contraseña (minimo 6 caracteres): ");
                        contrasenaReg = sc.nextLine().trim();
                        if (op.validarContrasena(contrasenaReg)) {
                            contrasenaValida = true;
                        } else {
                            System.out.println("La contraseña debe tener al menos 6 caracteres.");
                        }
                    }
 
                    String numDoc = "";
                    boolean docValido = false;
                    while (!docValido) {
                        System.out.print("Numero de documento: ");
                        numDoc = sc.nextLine().trim();
                        if (op.validarNumeroEntero(numDoc)) {
                            docValido = true;
                        } else {
                            System.out.println("El numero de documento debe ser numerico.");
                        }
                    }
 
                    Usuario usuario = new Usuario(correoReg, contrasenaReg, nombreUsuario, Integer.parseInt(numDoc));
                    String idUsuario = op.generarIdUsuario(op.getArreglo());
                    usuario.setId(idUsuario);
 
                    EmpresaAgricola empresa = new EmpresaAgricola(nombreEmpresa, ubicacion, anio, usuario);
                    String idEmpresa = op.generarIdEmpresa(op.getArreglo());
                    empresa.setId(idEmpresa);
 
                    op.getArreglo().add(empresa);
                    op.Serializar(op.getArreglo(), PATH, FILE);
 
                    System.out.println("\n-- Resumen del Registro --");
                    System.out.println("ID Empresa : " + idEmpresa);
                    System.out.println("Empresa    : " + nombreEmpresa);
                    System.out.println("Ubicacion  : " + ubicacion);
                    System.out.println("Anio fund. : " + anio);
                    System.out.println("ID Usuario : " + idUsuario);
                    System.out.println("Usuario    : " + nombreUsuario);
                    System.out.println("Correo     : " + correoReg);
                    System.out.println("Documento  : " + numDoc);
                    System.out.println("Registro exitoso.");
                    break;
                }
 
                case "0": {
                    System.out.println("Hasta luego.");
                    salir = true;
                    break;
                }
 
                default: {
                    System.out.println("Opcion no valida. Intente de nuevo.");
                }
            }
        }
 
        sc.close();
    }
}