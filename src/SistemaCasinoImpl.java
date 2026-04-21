//Para leer los documentos txt
import ucn.ArchivoEntrada;
import ucn.ArchivoSalida;
import ucn.Registro;

//Para manejar las excepciones dentro de la lectura de datos
import java.io.IOException;

//Para manejar la impresion y lectura
import ucn.StdOut;
import ucn.StdIn;

//Para procesar el dia que contiene el sistema
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Para determinar el resultado de la Jugada
import java.util.Random;



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
    public void cargaDeDatos() throws IOException {
        ArchivoEntrada archivoEntradaClientes = new ArchivoEntrada("clientes.txt");
        while(!archivoEntradaClientes.isEndFile()){

            Registro regEnt = archivoEntradaClientes.getRegistro();
            String rut = regEnt.getString();
            String nombre = regEnt.getString();
            String apellidoPaterno = regEnt.getString();
            String apellidoMaterno = regEnt.getString();
            String nombreUsuario = regEnt.getString();
            String contrasenia = regEnt.getString();
            String categoria = regEnt.getString();

            ingresarCliente(rut, nombre, apellidoPaterno, apellidoMaterno,nombreUsuario,contrasenia, categoria);
        }

        ArchivoEntrada archivoEntradaJugadas = new ArchivoEntrada("jugadas.txt");
        while(!archivoEntradaJugadas.isEndFile()){

            Registro regEnt = archivoEntradaJugadas.getRegistro();
            String rutCliente = regEnt.getString();
            int idMesa = regEnt.getInt();
            String fecha = regEnt.getString();
            int monto = regEnt.getInt();
            String resultado = regEnt.getString();

            ingresarJugada(rutCliente,idMesa,fecha,monto,resultado);
        }

        ArchivoEntrada archivoEntradaMesas = new ArchivoEntrada("mesas.txt");
        while(!archivoEntradaMesas.isEndFile()){

            Registro regEnt = archivoEntradaMesas.getRegistro();
            int id = regEnt.getInt();
            String tipoJuego = regEnt.getString();
            String descripcion = regEnt.getString();
            int montoMin = regEnt.getInt();
            int montoMax = regEnt.getInt();
            String estado = regEnt.getString();

            ingresarMesa(id, tipoJuego, descripcion, montoMin, montoMax, estado);
        }
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
    public boolean ingresarJugada(String rutCliente,int idMesa, String fecha, int apuesta, String resultado) {
        Jugada jugada = new Jugada(rutCliente, idMesa, fecha, apuesta, resultado);
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
        StringBuilder mensaje = new StringBuilder();

        for(int i = 0; i < this.contenedorMesas.getCantidadActual(); i++){
            Mesa m = this.contenedorMesas.obtenerMesa(i);

            if(m.getEstado().equals("Disponible")){

                mensaje.append("**********\n");
                mensaje.append("ID: ").append(m.getId()).append("\n");
                mensaje.append("Juego: ").append(m.getTipoJuego()).append("\n");
                mensaje.append("Descripción: ").append(m.getDescripcion()).append("\n");
                mensaje.append("Apuesta minima: ").append(m.getApuestaMin()).append("\n");
                mensaje.append("Apuesta Maxima: ").append(m.getApuestaMax()).append("\n");
                mensaje.append("Estado: ").append(m.getEstado()).append("\n");
                mensaje.append("**********\n\n");
            }
        }
        return mensaje.toString();
    }

    @Override
    public boolean registrarSesionJuego(int id, int monto, String nombreUsuario){
        //Se obtiene la fecha actual del dispositivo y se establecce con el orden dia/Mes/año
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = fechaActual.format(formatoFecha);

        //Con el nombre de usuario del cliente guardado al iniciar sesion se obtiene su rut
        Cliente c = this.contenedorClientes.buscarCliente(nombreUsuario);
        String rut = c.getRut();

        //Asociar la mesa que eligio el cliente
        Mesa m = this.contenedorMesas.obtenerMesaPorId(id);
        if(m == null || !m.getEstado().equals("Disponible")) {return false;}

        if(monto < m.getApuestaMin() || monto > m.getApuestaMax()){ return false;}

        //Obtener resultado
        Random random = new Random();
        double suerte = random.nextDouble();

        String resultado = "";

        if(suerte < 0.45){
            //Se determino una jugada Ganada
            resultado = "Ganada";
        } else {
            // Se determino una jugada Perdida
            resultado = "Perdida";
        }
        // Crear y Agregar la jugada
        Jugada j = new Jugada(rut, id, fecha, monto, resultado);
        this.contenedorJugadas.agregarJugada(j);
        return true;
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
