//Para leer los documentos txt
import ucn.ArchivoEntrada;
import ucn.Registro;

//Para manejar las excepciones dentro de la lectura de datos
import java.io.IOException;

//Para manejar la impresion y lectura

//Para procesar el dia que contiene el sistema
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Para determinar el resultado de la Jugada
import java.util.Random;

// Se asociada el implementador con la interface
public class SistemaCasinoImpl implements SistemaCasino{
    private ContenedorClientes contenedorClientes;
    private ContenedorMesas contenedorMesas;
    private ContenedorJugadas contenedorJugadas;

    //Se crea la funcion para inicializar la cantidad maxima por lista
    public SistemaCasinoImpl(int cantMaxMesa, int cantMaxJugada, int cantMaxClientes){
        this.contenedorClientes = new ContenedorClientes(cantMaxClientes);
        this.contenedorJugadas = new ContenedorJugadas(cantMaxJugada);
        this.contenedorMesas = new ContenedorMesas(cantMaxMesa);
    }

    //Carga de datos de los archivos txt jugadas, clientes, mesas.
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
        ArchivoEntrada archivoEntradaJugadas = new ArchivoEntrada("jugadas.txt");
        while(!archivoEntradaJugadas.isEndFile()){

            Registro regEnt = archivoEntradaJugadas.getRegistro();
            String rutCliente = regEnt.getString();
            int idMesa = regEnt.getInt();
            String fecha = regEnt.getString();
            int monto = regEnt.getInt();
            String resultado = regEnt.getString();

            Mesa mesa = contenedorMesas.obtenerMesaPorId(idMesa);
            ingresarJugada(rutCliente,mesa,fecha,monto,resultado);
        }
    }

    //Funcion para ingresar un cliente
    @Override
    public boolean ingresarCliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String contrasenia, String categoriaSocio) {
        Cliente cliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, nombreUsuario, contrasenia, categoriaSocio);
        return this.contenedorClientes.agregarCliente(cliente);
    }
    //Funcion para ingresar una mesa
    @Override
    public boolean ingresarMesa(int id, String tipoJuego, String descripcion, int apuestaMin, int apuestaMax, String estado) {
        Mesa mesa = new Mesa(id, tipoJuego, descripcion, apuestaMin, apuestaMax,estado);
        return this.contenedorMesas.agregarMesa(mesa);
    }
    //Funcion para ingresar una Jugada
    @Override
    public boolean ingresarJugada(String rutCliente,Mesa mesa, String fecha, int apuesta, String resultado) {
        Jugada jugada = new Jugada(rutCliente, mesa, fecha, apuesta, resultado);
        return this.contenedorJugadas.agregarJugada(jugada);
    }

    // Funcion obtener cantidad actual de  mesas
    public int getCantidadActualMesas(){
        return contenedorMesas.getCantidadActual();
    }

    //Funcion para verificar los datos ingreasdos para iniciar sesion
    @Override
    public boolean iniciarSesion(String nombreUsuario, String contrasenia) {
        if(contenedorClientes.buscarClientePorNombre(nombreUsuario) != null){
            if(contenedorClientes.buscarClientePorNombre(nombreUsuario).getContrasenia().equals(contrasenia)){
                return true;
            }
        }
        return false;
    }

    // Despliega las mesas con el estado "Disponible"
    @Override
    public void desplegarMesaDisponible() {
        for(int i = 0; i < this.contenedorMesas.getCantidadActual(); i++){
            Mesa m = this.contenedorMesas.obtenerMesa(i);
            if(m.getEstado().equals("Disponible")){
                System.out.println("**********");
                System.out.println("ID: " + m.getId());
                System.out.println("Juego: " + m.getTipoJuego());
                System.out.println("Descripción: " + m.getDescripcion());
                System.out.println("Apuesta minima: " + m.getApuestaMin());
                System.out.println("Apuesta Maxima: " + m.getApuestaMax());
                System.out.println("Estado: " + m.getEstado());
                System.out.println("**********\n");
            }
        }
    }

    //Gerera todos los comprobantes de las jugadas asociadas a un cliente por rut
    @Override
    public void registrarSesionJuego(int id, int monto, String nombreUsuario){
        //Con el nombre de usuario del cliente guardado al iniciar sesion se obtiene su información para ser utilizado
        Cliente cliente = contenedorClientes.buscarClientePorNombre(nombreUsuario);

        int jugadasHoy = contenedorJugadas.contarJugadas(cliente.getRut());

        if(!contenedorClientes.puedeJugar(cliente.getCategoriaSocio(), jugadasHoy)){
            System.out.println("Ya no puede seguir jugando, ya alcanzó su limite de jugadas por hoy.");
        }else{
            //Asociar la mesa que eligio el cliente
            Mesa m = this.contenedorMesas.obtenerMesaPorId(id);

            if(m == null || !m.getEstado().equals("Disponible")) {
                System.out.println("No hay mesas disponibles para jugar.");
                return;
            }

            if(monto < m.getApuestaMin() || monto > m.getApuestaMax()){
                System.out.println("El monto ingresado no entra en el rango de la mesa.");
                return;
            }

            //Se obtiene la fecha actual del dispositivo y se establecce con el formato DD/MM/AAAA
            LocalDateTime fechaActual = LocalDateTime.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha = fechaActual.format(formatoFecha);

            //Obtener resultado
            Random random = new Random();
            double suerte = random.nextDouble();

            String resultado = "";

            if(suerte < 0.45){
                //Se determina una jugada Ganada
                resultado = "Ganada";
                System.out.println("\nFelicidades! la jugada ha sido: " + resultado);
            } else {
                // Se determina una jugada Perdida
                resultado = "Perdida";
                System.out.println("\nLastima, la jugada ha sido: " + resultado);
            }
            // Crear y Agregar la jugada
            System.out.println("Creando comprobante de la sesión...");
            Jugada j = new Jugada(cliente.getRut(), m, fecha, monto, resultado);
            this.contenedorJugadas.agregarJugada(j);
            comprobanteSesionJuego(cliente, j);
        }


    }

    //verifica y registra la jugada del cliente en una mesa
    @Override
    public void comprobanteSesionJuego(Cliente cliente, Jugada j) {
        System.out.println("*****************************************************");
        System.out.println("            COMPROBANTE DE SESIÓN DE JUEGO");
        System.out.println("Cliente: " + cliente.getNombreCompleto() + "        Categoría:" + cliente.getCategoriaSocio());
        System.out.println("Mesa: " + j.getMesa().getId() + " - " + j.getMesa().getTipoJuego());
        System.out.println("Apuesta: " + j.getApuesta());
        System.out.println("Fecha: " + j.getFechaJugada());
        System.out.println("Resultado: " + j.getResultado().toUpperCase());
        System.out.println("*****************************************************\n");
    }
    // Genera el comprobante de la jugada realizada por el cliente
    @Override
    public void consultarHistorial(String nombreUsuario) {
        Cliente cliente =  contenedorClientes.buscarClientePorNombre(nombreUsuario);
        int balance = 0;
        contenedorJugadas.ordenarJugadas();

        for (int i = 0; i < contenedorJugadas.getCantidadActualjugadas(); i++) {
            Jugada j = contenedorJugadas.getJugadas(i);
            if(j.getRutCliente().equals(cliente.getRut())){
                comprobanteSesionJuego(cliente, j);
                if(j.getResultado().equals("Ganada")){
                    balance +=  j.getApuesta();
                } else{
                    balance -=  j.getApuesta();
                }
            }
        }
        System.out.println("Balance total: " + balance);
    }
    public void verDatosPersonales(String nombreUsuario){
        Cliente c = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        System.out.println("*****************************************************");
        System.out.println("Nombre comple: "+ c.getNombreCompleto());
        System.out.println("Rut: "+ c.getRut());
        System.out.println("Usuario: "+ c.getNombreUsuario());
        System.out.println("Categoria"+ c.getCategoriaSocio());
        System.out.println("*****************************************************");
    }

    public boolean administrarMesas(String nombreUsuario) {
        Cliente cliente = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        if (cliente.getCategoriaSocio().equals("Platino")) {
            return true;
        } else {
            System.out.println("Acceso restringido. Solo disponible para clientes Platino.");
            return false;
        }
    }
    public void subirCategoria(String nombreUsuario, Jugada j){
        Cliente c = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        String rut = c.getRut();

    }

    public boolean cambiarContrasenia(String nombreUsuario, String contraseniaActual, String nuevaContrasenia){
        //Obtenemos el cliente
        Cliente c = contenedorClientes.buscarClientePorNombre(nombreUsuario);

        //Comparamos la contraseña ingresada con la actual
        if(contraseniaActual.equals(c.getContrasenia())){
            //Analisis de la nueva contraseña
            if(nuevaContrasenia.length() >= 8){

                boolean tieneMayus = false;
                boolean tieneNumero = false;

                for(char texto : nuevaContrasenia.toCharArray()) {
                    if (Character.isUpperCase(texto)) tieneMayus = true;
                    if (Character.isDigit(texto)) tieneNumero = true;
                    }
                //Se establece la nueva contraseña con exito
                if(tieneMayus && tieneNumero){
                    System.out.println("Se cambio la contraseña con exito");
                    c.setContrasenia(nuevaContrasenia);
                    return true;
                }
            }
        }
        System.out.println("Error, alguna de las condiciones no se cumplieron");
        return false;
    }
    public void desplegarMesas(){
        for(int i = 0; i < this.contenedorMesas.getCantidadActual(); i++){
            Mesa m = this.contenedorMesas.obtenerMesa(i);
            System.out.println("**********");
            System.out.println("ID: " + m.getId());
            System.out.println("Juego: " + m.getTipoJuego());
            System.out.println("Descripción: " + m.getDescripcion());
            System.out.println("Apuesta minima: " + m.getApuestaMin());
            System.out.println("Apuesta Maxima: " + m.getApuestaMax());
            System.out.println("Estado: " + m.getEstado());
            System.out.println("**********\n");
        }
    }
}
