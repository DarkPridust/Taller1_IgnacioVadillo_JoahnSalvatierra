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
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
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
}


