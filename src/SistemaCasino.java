public interface SistemaCasino {

    // El sistema carga datos
    void cargaDeDatos();

    // El sistema permite ingresar nuevos objetos en las clases: Cliente, Mesa, Jugada
    public boolean ingresarCliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String contrasenia, String categoria);
    public boolean ingresarMesa(int id, String tipoJuego, String descripcion, int apuestaMin, int apuestaMax, String estado);
    public boolean ingresarJugada(String fecha, int apuesta, String resultado);

    //Operaciones del sistema
    public boolean iniciarSesion(String nombreUsuario, String contrasenia);
    public String desplegarMesaDisponible();
    public String registrarSesionJuego(String fecha, double resultado);
    public String comprobanteSesionJuego();
    public String consultarHistorial(String fecha);
}
