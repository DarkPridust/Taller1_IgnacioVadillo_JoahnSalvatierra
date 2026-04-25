import java.io.IOException;

public interface SistemaCasino {

    // El sistema carga datos
    void cargaDeDatos() throws IOException;

    // El sistema permite ingresar nuevos objetos en las clases: Cliente, Mesa, Jugada
    public boolean ingresarCliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String contrasenia, String categoria);
    public boolean ingresarMesa(int id, String tipoJuego, String descripcion, int apuestaMin, int apuestaMax, String estado);
    public boolean ingresarJugada(String rutCliente, Mesa mesa, String fecha, int apuesta, String resultado);

    // Obtener cantidad actual de cada lista
    public int getCantidadActualMesas();

    //Operaciones del sistema
    public boolean iniciarSesion(String nombreUsuario, String contrasenia);
    public void desplegarMesaDisponible();
    public void registrarSesionJuego(int id, int monto, String nombreUsuario);
    public void comprobanteSesionJuego(Cliente cliente, Jugada j);
    public void consultarHistorial(String nombreUsuario);
    public boolean accesoAdministrarMesas(String nombreUsuario);
    public void cambiarEstadoMesa(int id);
    public void verDatosPersonales(String nombreUsuario);
    public void subirCategoria(String nombreusuario, String eleccion);
    public boolean cambiarContrasenia(String nombreUsuario, String contraseniaActual, String nuevaContrasenia);
    public void desplegarMesas();
    public void desplegarEstadistica();

    // El sistema sube los datos
    void subirDatos() throws IOException;

}
