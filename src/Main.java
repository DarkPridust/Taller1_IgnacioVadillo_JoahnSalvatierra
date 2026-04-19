//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import ucn.StdIn;
import ucn.StdOut;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

    // iniciar el sistema
    SistemaCasino sistema =new SistemaCasinoImpl(100,10000,1000);
    sistema.cargaDeDatos();
    iniciarSistemaCasino(sistema);

    }

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
                        int opcionInicio = 0;

                        while(opcion != 7){
                            System.out.println("----- Bienvenido " + nombreUsuario + "-----");

                            StdIn.readInt();
                            StdOut.println("--------------------");
                        }

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
}


