import ucn.ArchivoEntrada;
import ucn.ArchivoSalida;
import ucn.Registro;

import java.io.IOException;


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
