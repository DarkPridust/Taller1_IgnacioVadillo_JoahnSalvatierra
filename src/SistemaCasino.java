import java.io.IOException;

public interface SistemaCasino {

    // El sistema carga datos
    void cargaDeDatos() throws IOException;

    // El sistema permite ingresar nuevos objetos en las clases: Cliente, Mesa, Jugada
    boolean ingresarCliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String contrasenia, String categoria);
    boolean ingresarMesa(int id, String tipoJuego, String descripcion, int apuestaMin, int apuestaMax, String estado);
    boolean ingresarJugada(Cliente cliente, Mesa mesa, String fecha, int apuesta, String resultado);

    //Operaciones del sistema
    void registroNuevoCliente();
    boolean iniciarSesion(String nombreUsuario, String contrasenia);
    void desplegarMesaDisponible();
    void registrarSesionJuego(String nombreUsuario, int jugadas);
    void comprobanteSesionJuego(Cliente cliente, Jugada j);
    void consultarHistorial(String nombreUsuario);
    boolean accesoAdministrarMesas(String nombreUsuario);
    void cambiarEstadoMesa();
    void crearYAgregarMesa();
    void verDatosPersonales(String nombreUsuario);
    void subirCategoria(String nombreusuario);
    boolean cambiarContrasenia(String nombreUsuario, String contraseniaActual);
    void desplegarMesas();
    void desplegarEstadistica();

    // El sistema sube los datos
    void subirDatos() throws IOException;

}
