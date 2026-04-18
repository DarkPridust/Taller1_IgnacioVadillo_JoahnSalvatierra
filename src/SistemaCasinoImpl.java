public class SistemaCasinoImpl implements SistemaCasino{
    private ContenedorClientes contenedorClientes;
    private ContenedorMesas contenedorMesas;
    private ContenedorJugadas contenedorJugadas;

    public SistemaCasinoImpl(int cantMaxMesa, int cantMaxJugada, int cantMaxClientes){
        this.contenedorClientes = new ContenedorClientes(cantMaxClientes);
        this.contenedorJugadas = new ContenedorJugadas(cantMaxJugada);
        this.contenedorMesas = new ContenedorMesas(cantMaxMesa);
    }

    @Override
    public void cargaDeDatos(){
    }

    @Override
    public boolean ingresarCliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String contrasenia, String categoriaSocio) {
        Cliente cliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, nombreUsuario, contrasenia, categoriaSocio);
        return this.contenedorClientes.agregarCliente(cliente);
    }

    @Override
    public boolean ingresarMesa(int id, String tipoJuego, String descripcion, int apuestaMin, int apuestaMax, String estado) {
        Mesa mesa = new Mesa(id, tipoJuego, descripcion, apuestaMin, apuestaMax,estado);
        return this.contenedorMesas.agregarMesa(mesa);
    }

    @Override
    public boolean ingresarJugada(String fecha, int apuesta, String resultado) {
        Jugada jugada = new Jugada(fecha, apuesta, resultado);
        return this.contenedorJugadas.agregarJugada(jugada);
    }

    @Override
    public boolean iniciarSesion(String nombreUsuario, String contrasenia) {
        Cliente c = contenedorClientes.buscarCliente(nombreUsuario);
        if(c != null){
            if(c.getContrasenia().equals(contrasenia)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String desplegarMesaDisponible() {
        return "";
    }

    @Override
    public String registrarSesionJuego(String fecha, double resultado) {
        return "";
    }

    @Override
    public String comprobanteSesionJuego() {
        return "";
    }

    @Override
    public String consultarHistorial(String fecha) {
        return "";
    }
}
