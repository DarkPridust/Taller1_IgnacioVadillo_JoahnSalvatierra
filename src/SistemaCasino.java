import java.io.IOException;

public interface SistemaCasino {

    // El sistema carga datos
    void cargaDeDatos() throws IOException;
    boolean datosCargados();

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
