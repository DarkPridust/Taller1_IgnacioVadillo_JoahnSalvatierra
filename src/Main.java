//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import ucn.StdIn;
import ucn.StdOut;

public class Main {
    public static void main(String[] args) {

    // iniciar el sistema
    SistemaCasino sistemaCasino =new SistemaCasinoImpl(100,10000,1000);
    sistemaCasino.cargaDeDatos();
    iniciarSistemaCasino();





    }

    public static void iniciarSistemaCasino(){
        int opcion = 0;

        while(opcion != 3){
            StdOut.println("[1] Iniciar sesion");
            StdOut.println("[2] Registrar cliente");
            StdOut.println("[3] Salir");
            opcion = StdIn.readInt();

            switch(opcion){
                case 1:
                    StdOut.println("--------------------");

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


