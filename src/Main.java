//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import ucn.StdIn;
import ucn.StdOut;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

    // inicio del sistema
    SistemaCasino sistema = new SistemaCasinoImpl(100,10000,1000);
    sistema.cargaDeDatos();
    iniciarSistemaCasino(sistema);
    }

    //Menu principal del sistema
    public static void iniciarSistemaCasino(SistemaCasino sistema){
        int opcion = 0;

        while(opcion != 3){
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
                        StdOut.println("Usuarion o contraseña erronea");
                    }
                    StdOut.println("--------------------");
                    break;

                case 2:
                    StdOut.println("--------------------");

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
        }
    }

    //Menu del usuario una vez inicia sesion
    public static void menuUsuario(SistemaCasino sistema, String nombreUsuario){
        int opcion1 = 0;
        while(opcion1 != 7){
            StdOut.println("----- Bienvenido " + nombreUsuario + "-----");
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
                    StdOut.println(sistema.desplegarMesaDisponible());
                    StdOut.println("Ingrese el id de la mesa en la que desea apostar: ");
                    int id = StdIn.readInt();

                    StdOut.println("Ingrese el monto de su apuesta: ");
                    int monto = StdIn.readInt();

                    sistema.registrarSesionJuego(id, monto, nombreUsuario);
                    break;

                case 2:
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
        }
    }
}


