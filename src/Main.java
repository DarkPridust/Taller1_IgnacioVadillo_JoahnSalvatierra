//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import ucn.StdIn;
import ucn.StdOut;

import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws IOException {

    // Inicio del sistema
    SistemaCasinoImpl sistema = new SistemaCasinoImpl(100,10000,1000);
    sistema.cargaDeDatos();
    iniciarSistemaCasino(sistema);
    }

    /**
     * Este metodo inicia el programa principal
     * @param sistema Es el sistema
     * @throws IOException
     */
    public static void iniciarSistemaCasino(SistemaCasinoImpl sistema) throws IOException {
        int opcion = 0;
        do{
            StdOut.println("==== Casino Royal Aires ====");
            StdOut.println("[1] Iniciar sesión");
            StdOut.println("[2] Registrar cliente");
            StdOut.println("[3] Salir");
            StdOut.println("Ingrese una opción: ");
            try{
                opcion = StdIn.readInt();
                switch(opcion) {
                    case 1:
                        StdOut.println("------- Inicio de Sesión --------");

                        StdOut.println("Ingrese el nombre de usuario con el que desea iniciar sesión: ");
                        String nombreUsuario = StdIn.readString();

                        StdOut.println("Ingrese su contraseña: ");
                        String contrasenia = StdIn.readString();
                        boolean inicioExitoso = sistema.iniciarSesion(nombreUsuario, contrasenia);
                        if (inicioExitoso) {
                            menuUsuario(sistema, nombreUsuario);
                        } else {
                            StdOut.println("Credenciales inválidas. Intente nuevamente.");
                        }
                        StdOut.println("--------------------");
                        break;

                    case 2:
                        StdOut.println("--------------------");
                        sistema.registroNuevoCliente();
                        StdOut.println("--------------------");
                        break;

                    case 3:
                        StdOut.println("--------------------");
                        StdOut.println("Cerrando el sistema");
                        StdOut.println("--------------------");
                        sistema.subirDatos();
                        break;

                    default:
                        StdOut.println("La opción ingresada es incorrecta");
                        break;
                }
            } catch(InputMismatchException e){
                System.out.println("Debe de ingresar un digito entero.");
                break;
            }
        }while(opcion != 3);
    }

    /**
     * Este metodo inicia el menú del usuario
     * @param sistema Es el sistema
     * @param nombreUsuario Es el usuario iniciado sesión en el sistema
     */
    public static void menuUsuario(SistemaCasinoImpl sistema, String nombreUsuario) {
        int opcion1 = 0;
        int jugadasHoy = 0;
        do{
            StdOut.println("\n----- Bienvenido " + nombreUsuario + "-----");
            StdOut.println("[1] Registrar sesión de juego");
            StdOut.println("[2] Consultar historial");
            StdOut.println("[3] Gestionar cuenta");
            StdOut.println("[4] Ver mesas disponibles");
            StdOut.println("[5] Administrar Mesas");
            StdOut.println("[6] Estadísticas");
            StdOut.println("[7] Cerrar Sesión");
            StdOut.println("Ingrese una opción: ");
            opcion1 = StdIn.readInt();

            switch(opcion1){
                case 1:
                    sistema.registrarSesionJuego(nombreUsuario, jugadasHoy);
                    jugadasHoy++;
                    break;

                case 2:
                    sistema.consultarHistorial(nombreUsuario);
                    break;

                case 3:
                    menuGestionarCuenta(sistema, nombreUsuario);
                    break;

                case 4:
                    sistema.desplegarMesaDisponible();
                    break;

                case 5:
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

    /**
     * Este metodo inicia el menú para gestionar la cuenta
     * @param sistema Es el sistema
     * @param nombreUsuario Es el usuario iniciado sesión en el sistema
     */
    public static void menuGestionarCuenta(SistemaCasinoImpl sistema, String nombreUsuario){
        int opcionG = 0;
        do{
            StdOut.println("\n===== Gestion de cuenta =====");
            StdOut.println("[1] Ver datos personales");
            StdOut.println("[2] Subir de categoria");
            StdOut.println("[3] Cambiar contraseña");
            StdOut.println("[4] Volver");
            StdOut.println("Ingrese una opción: ");
            opcionG = StdIn.readInt();

            switch(opcionG){

                case 1:
                    sistema.verDatosPersonales(nombreUsuario);
                    break;

                case 2:
                    sistema.subirCategoria(nombreUsuario);
                    break;

                case 3:
                    StdOut.println("Ingrese la contraseña actual: ");
                    String contraseniaActual = StdIn.readString();
                    do{
                        sistema.cambiarContrasenia(nombreUsuario, contraseniaActual);
                    }while (!sistema.cambiarContrasenia(nombreUsuario, contraseniaActual));
                    break;

                case 4:
                    StdOut.println("Volviendo al menú anterior");
                    break;

                default:
                    StdOut.println("La opción ingresada es invalida");
                    break;
            }
        } while(opcionG != 4);
    }

    /**
     * Este metodo inicia el menú de administrar las mesas
     * @param sistema Es el sistema
     * @param nombreUsuario Es el usuario iniciado sesión en el sistema
     */
    public static void menuAdministrarMesas(SistemaCasinoImpl sistema, String nombreUsuario){
        boolean permiso = sistema.accesoAdministrarMesas(nombreUsuario);
        if(permiso){
            int opcionM = 0;
            do{
                StdOut.println("===== Administración de mesas =====");
                StdOut.println("[1] Agregar nueva Mesa");
                StdOut.println("[2] Cambiar estado de una mesa");
                StdOut.println("[3] Volver al menu anterior");
                StdOut.println("Ingrese una opción: ");
                opcionM = StdIn.readInt();

                switch(opcionM){

                    case 1:
                        sistema.crearYAgregarMesa();
                        break;

                    case 2:
                        sistema.desplegarMesas();
                        sistema.cambiarEstadoMesa();
                        break;

                    case 3:
                        StdOut.println("Volviendo al menu anterior...\n");
                        break;

                    default:
                        StdOut.println("La opción ingresada es invalida");
                        break;
                }
            } while(opcionM != 3);
        }
    }
}



