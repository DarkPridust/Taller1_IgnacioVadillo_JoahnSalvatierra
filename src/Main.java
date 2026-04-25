//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import ucn.StdIn;
import ucn.StdOut;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

    // inicio del sistema
    SistemaCasinoImpl sistema = new SistemaCasinoImpl(100,10000,1000);
    sistema.cargaDeDatos();
    iniciarSistemaCasino(sistema);
    }

    //Menu principal del sistema
    public static void iniciarSistemaCasino(SistemaCasinoImpl sistema){
        int opcion = 0;
        do{
            StdOut.println("==== Casino Royal Aires ====");
            StdOut.println("[1] Iniciar sesion");
            StdOut.println("[2] Registrar cliente");
            StdOut.println("[3] Salir");
            opcion = StdIn.readInt();

            switch(opcion) {
                case 1:
                    StdOut.println("------- Inicio de Sesion --------");

                    StdOut.println("Ingrese el nombre de usuario con el que desea iniciar sesion");
                    String nombreUsuario = StdIn.readString();

                    StdOut.println("Ingrese su contraseña");
                    String contrasenia = StdIn.readString();
                    boolean inicioExitoso = sistema.iniciarSesion(nombreUsuario, contrasenia);
                    if (inicioExitoso) {
                        menuUsuario(sistema, nombreUsuario);
                    } else {
                        StdOut.println("Usuario o contraseña erronea");
                    }
                    StdOut.println("--------------------");
                    break;

                case 2:
                    StdOut.println("--------------------");
                    registroNuevoCliente(sistema);
                    StdOut.println("--------------------");
                    break;

                case 3:
                    StdOut.println("--------------------");
                    StdOut.println("Cerrando el sistema");
                    StdOut.println("--------------------");
                    sistema.subirDatos();
                    break;

                default:
                    StdOut.println("La opcion ingresada es incorrecta");
                    break;
            }
        }while(opcion != 3);
    }

    //Menu del usuario una vez inicia sesion
    public static void menuUsuario(SistemaCasinoImpl sistema, String nombreUsuario) {
        int opcion1 = 0;
        do{
            StdOut.println("\n----- Bienvenido " + nombreUsuario + "-----");
            StdOut.println("[1] Registrar sesion de juego");
            StdOut.println("[2] Consultar historial");
            StdOut.println("[3] Gestionar cuenta");
            StdOut.println("[4] Ver mesas disponibles");
            StdOut.println("[5] Administrar Mesas");
            StdOut.println("[6] Estadisticas");
            StdOut.println("[7] Cerrar Sesion");
            opcion1 = StdIn.readInt();

            switch(opcion1){
                case 1:
                    sistema.desplegarMesaDisponible();
                    StdOut.println("Ingrese el id de la mesa en la que desea apostar: ");
                    int id = StdIn.readInt();
                    StdOut.println("Ingrese el monto de su apuesta: ");
                    int monto = StdIn.readInt();
                    sistema.registrarSesionJuego(id, monto, nombreUsuario);
                    break;

                case 2:
                    sistema.consultarHistorial(nombreUsuario);
                    break;

                case 3:
                    gestionarCuenta(sistema, nombreUsuario);
                    break;

                case 4:
                    // Se despliegan todas las mesas disponibles
                    sistema.desplegarMesaDisponible();
                    break;

                case 5:
                    // se llama a la funcion para vereificar categoria del cliente y proceder
                    menuAdministrarMesas(sistema, nombreUsuario);
                    break;

                case 6:
                    sistema.desplegarEstadistica();
                    break;

                case 7:
                    StdOut.println("Cerrando sesion actual");
                    break;

                default:
                    StdOut.println("La opcion ingresada es incorrecta");
                    break;
            }
        }while(opcion1 != 7);
    }

    public static void registroNuevoCliente(SistemaCasinoImpl sistema){
        StdOut.println("------- Registrar Cliente --------");
        StdOut.println("Ingrese el nombre del nuevo cliente.");
        String nombre = StdIn.readString();
        StdOut.println("Ingrese el apellido paterno del nuevo cliente.");
        String apellidoPaterno = StdIn.readString();
        StdOut.println("Ingrese el apellido materno del nuevo cliente.");
        String apellidoMaterno = StdIn.readString();
        StdOut.println("Ingrese el RUT del nuevo cliente (En formato: 12.345.678-9).");
        String rutCliente = StdIn.readString();
        StdOut.println("Ingrese el nombre de usuario del nuevo cliente.");
        String nombreUsuario = StdIn.readString();

        String rutSinVerificador = rutCliente.replaceAll("[^0-9]", "");
        String contrasenia = rutSinVerificador.substring(0, rutSinVerificador.length()-1);

        sistema.ingresarCliente(rutCliente,nombre,apellidoPaterno,apellidoMaterno,nombreUsuario,contrasenia,"Normal");
        System.out.print("El cliente ha sido registrado exitosamente.\n");
    }

    public static void gestionarCuenta(SistemaCasinoImpl sistema, String nombreUsuario){
        int opcionG = 0;
        do{
            StdOut.println("\n===== Gestion de cuenta =====");
            StdOut.println("[1] Ver datos personales");
            StdOut.println("[2] Subir de categoria");
            StdOut.println("[3] Cambiar contraseña");
            StdOut.println("[4] Volver");
            opcionG = StdIn.readInt();

            switch(opcionG){

                case 1:
                    //Imprime los datos personales del usuario
                    sistema.verDatosPersonales(nombreUsuario);
                    break;

                case 2:

                    //sistema.subirCategoria();
                    break;

                case 3:
                    //Solicita la contraseña actual
                    StdOut.println("Ingrese la contrase actual: ");
                    String contraseniaActual = StdIn.readString();

                    //Solicita la nueva contraseña
                    StdOut.println("Requisitos: La nueva contraseña debe de ser diferente a la anterrior" +
                            "contener al menos 1 mayuscula, contener al menos un número y debe de superar o ser igual a 8 caracteres");
                    StdOut.println("Ingrese la nueva contraseña");
                    String nuevaContrasenia = StdIn.readString();

                    //Llama a la funcion nuevaContrania para validar lo ingresado
                    sistema.cambiarContrasenia(nombreUsuario, contraseniaActual, nuevaContrasenia);
                    break;

                case 4:
                    StdOut.println("Volviendo al menú anterior");
                    break;

                default:
                    StdOut.println("La opcion ingresada es invalida");
                    break;
            }
        } while(opcionG != 4);
    }

    public static void menuAdministrarMesas(SistemaCasinoImpl sistema, String nombreUsuario){
        boolean permiso = sistema.accesoAdministrarMesas(nombreUsuario);
        if(permiso){
            int opcionM = 0;
            do{
                StdOut.println("===== Administracion de mesas =====");
                StdOut.println("[1] Agregar nueva Mesa");
                StdOut.println("[2] Cambiar estado de una mesa");
                StdOut.println("[3] Volver al menu anterior");
                opcionM = StdIn.readInt();

                switch(opcionM){

                    case 1:
                        StdOut.println("------- Crear Mesa --------");
                        StdOut.println("Ingrese el tipo de juego de la mesa: ");
                        String tipo = StdIn.readString();
                        StdOut.println("Ingrese la descripcion de la mesa");
                        String descripcion = StdIn.readString();
                        StdOut.println("Ingresa la apuesta minima de la mesa");
                        int apuestaMin =StdIn.readInt();
                        StdOut.println("Ingresa la apuesta maxima de la mesa");
                        int apuestaMax = StdIn.readInt();
                        StdOut.println("Ingrese el estado de la mesa (Disponible/Cerrada)");
                        String estado = StdIn.readString();

                        int idMesa = sistema.getCantidadActualMesas();

                        sistema.ingresarMesa(idMesa + 1, tipo, descripcion, apuestaMin, apuestaMax, estado);

                    case 2:
                        sistema.desplegarMesas();
                        StdOut.println("Ingrese el id de la mesa que desee cambiar de estado");
                        int id = StdIn.readInt();
                        sistema.cambiarEstadoMesa(id);

                    case 3:
                        StdOut.println("Volviendo al menu anterior...\n");
                        break;

                    default:
                        StdOut.println("La opcion ingresada es invalida");

                }
            } while(opcionM != 3);
        }
    }
}

// TODO validar 1.1.3.3 por diferente error, 1.1.5.a // Realizar 1.1.3.2 y 1.1.5.b

