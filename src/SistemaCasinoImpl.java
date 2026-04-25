//Para leer los documentos txt
import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;

//Para manejar las excepciones dentro de la lectura de datos
import java.io.IOException;

//Para manejar la impresion y lectura

//Para procesar el dia que contiene el sistema
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Para determinar el resultado de la Jugada
import java.util.Random;

/**
 * Se asocia el implementador con la interface
 */
public class SistemaCasinoImpl implements SistemaCasino{
    private ContenedorClientes contenedorClientes;
    private ContenedorMesas contenedorMesas;
    private ContenedorJugadas contenedorJugadas;

    /**
     * Funcion que crea inicializa el sistema
     * @param cantMaxMesa un arreglo con la cantidad de mesas
     * @param cantMaxJugada un arrego con la cantidad de jugadas
     * @param cantMaxClientes un arreglo con la cantidad de clientes
     */
    public SistemaCasinoImpl(int cantMaxMesa, int cantMaxJugada, int cantMaxClientes){
        this.contenedorClientes = new ContenedorClientes(cantMaxClientes);
        this.contenedorJugadas = new ContenedorJugadas(cantMaxJugada);
        this.contenedorMesas = new ContenedorMesas(cantMaxMesa);
    }

    /**
     * Funcion que carga los datos de los archivos txt jugadas, clientes, mesas.
     * @throws IOException
     */
    @Override
    public void cargaDeDatos() throws IOException {
        // Lectura de clientes.txt
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

        // Lectura de mesas.txt
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
        // Lectura de jugadas.txt
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

    /**
     * Funcion que permite ingresar un cliente
     * @param rut un String con el rut del cliente
     * @param nombre un String con el nombre del cliente
     * @param apellidoPaterno un String con el apellido paterno del cliente
     * @param apellidoMaterno un String con el apellido materno del cliente
     * @param nombreUsuario un String con el nombre de usuario del cliente
     * @param contrasenia un String con la contraseña del cliente
     * @param categoriaSocio un String con la categoria del cliente
     * @return un booleano que determina si se pudo o no ingresar el cliente
     */
    @Override
    public boolean ingresarCliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String contrasenia, String categoriaSocio) {
        Cliente cliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, nombreUsuario, contrasenia, categoriaSocio);
        return this.contenedorClientes.agregarCliente(cliente);
    }

    /**
     * Funcion que permite ingresar una mesa
     * @param id un entero con el id de la mesa
     * @param tipoJuego un String con el tipo de juego de la mesa
     * @param descripcion un String con la descripcion de la mesa
     * @param apuestaMin un entero con la apuesta minima de la mesa
     * @param apuestaMax un entero con la apuesta maxima de la mesa
     * @param estado un String con el estado de la mesa
     * @return un booleano que determina si se pudo o no ingresar la mesa
     */
    @Override
    public boolean ingresarMesa(int id, String tipoJuego, String descripcion, int apuestaMin, int apuestaMax, String estado) {
        Mesa mesa = new Mesa(id, tipoJuego, descripcion, apuestaMin, apuestaMax,estado);
        return this.contenedorMesas.agregarMesa(mesa);
    }

    /**
     * Funcion que permite ingresar una jugada
     * @param rutCliente un String con el rut del cliente
     * @param mesa un objeto de tipo Mesa
     * @param fecha un String con la fecha de la jugada
     * @param apuesta un entero con la apuesta realizada
     * @param resultado un String con el resultado de la jugada
     * @return un booleano que determina si se pudo o no ingresar la jugada
     */
    @Override
    public boolean ingresarJugada(String rutCliente,Mesa mesa, String fecha, int apuesta, String resultado) {
        Jugada jugada = new Jugada(rutCliente, mesa, fecha, apuesta, resultado);
        return this.contenedorJugadas.agregarJugada(jugada);
    }

    /**
     * Funcion que obtiene la cantidad actual de mesas
     * @return un entero con la cantidad actual de mesas
     */
    public int getCantidadActualMesas(){
        return contenedorMesas.getCantidadActual();
    }

    public void registroNuevoCliente(){
        StdOut.println("------- Registrar Cliente --------");
        StdOut.println("Ingrese el nombre del nuevo cliente.");
        String nombre = StdIn.readString();
        StdOut.println("Ingrese el apellido paterno del nuevo cliente.");
        String apellidoPaterno = StdIn.readString();
        StdOut.println("Ingrese el apellido materno del nuevo cliente.");
        String apellidoMaterno = StdIn.readString();
        StdOut.println("Ingrese el RUT del nuevo cliente (En formato: 12.345.678-9).");
        String rutCliente = StdIn.readString();
        StdOut.println("Ingrese el nombre de usuario del nuevo cliente.");
        String nombreUsuario = StdIn.readString();

        String rutSinVerificador = rutCliente.replaceAll("[^0-9]", "");
        String contrasenia = rutSinVerificador.substring(0, rutSinVerificador.length()-1);

        ingresarCliente(rutCliente,nombre,apellidoPaterno,apellidoMaterno,nombreUsuario,contrasenia,"Normal");
        System.out.print("El cliente ha sido registrado exitosamente.\n");
    }

    /**
     * Funcion que verifica los datos ingreasdos para iniciar sesion
     * @param nombreUsuario un String con el nombre de usuario
     * @param contrasenia un String con la contraseña del usuario
     * @return un booleano que determina si se pudo o no iniciar sesion
     */
    @Override
    public boolean iniciarSesion(String nombreUsuario, String contrasenia) {
        if(contenedorClientes.buscarClientePorNombre(nombreUsuario) != null){
            if(contenedorClientes.buscarClientePorNombre(nombreUsuario).getContrasenia().equals(contrasenia)){
                return true;
            }
        }
        return false;
    }

    /**
     * Funcion que despliega las mesas con el estado "Disponible"
     */
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

    /**
     * Funcion que verifica y registra la jugada del cliente en una mesa
     * @param nombreUsuario un String con el nombre de usuario
     * @param jugadasHoy la cantidad de jugadas realizadas por hoy
     */
    @Override
    public void registrarSesionJuego(String nombreUsuario, int jugadasHoy){
        //Con el nombre de usuario del cliente guardado al iniciar sesion se obtiene su información para ser utilizado
        Cliente cliente = contenedorClientes.buscarClientePorNombre(nombreUsuario);

        if(!contenedorClientes.puedeJugar(cliente.getCategoriaSocio(), jugadasHoy)){
            System.out.println("Ya no puede seguir jugando, ya alcanzó su limite de jugadas por hoy.");
        }else{
            desplegarMesaDisponible();
            StdOut.println("Ingrese el id de la mesa en la que desea apostar: ");
            int id = StdIn.readInt();
            StdOut.println("Ingrese el monto de su apuesta: ");
            int monto = StdIn.readInt();

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

    /**
     * Funcion que genera el comprobante de la jugada realizada por el cliente
     * @param cliente un objeto de tipo Cliente
     * @param j un objeto de tipo Jugada
     */
    @Override
    public void comprobanteSesionJuego(Cliente cliente, Jugada j) {
        System.out.println("*****************************************************");
        System.out.println("            COMPROBANTE DE SESIÓN DE JUEGO");
        System.out.println("Cliente: " + cliente.getNombreCompleto() + "    Categoría:" + cliente.getCategoriaSocio());
        System.out.println("Mesa: " + j.getMesa().getId() + " - " + j.getMesa().getTipoJuego());
        System.out.println("Apuesta: " + j.getApuesta());
        System.out.println("Fecha: " + j.getFechaJugada());
        System.out.println("Resultado: " + j.getResultado().toUpperCase());
        System.out.println("*****************************************************\n");
    }

    /**
     * Funcion que gerera todos los comprobantes de las jugadas asociadas a un cliente por rut
     * @param nombreUsuario un String conn el nombre de usuario
     */
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

    /**
     * Funcion que permite ver los datos personales del usuario
     * @param nombreUsuario un String con el nombre de usuario
     */
    public void verDatosPersonales(String nombreUsuario){
        Cliente c = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        System.out.println("*****************************************************");
        System.out.println("                  DATOS PERSONALES");
        System.out.println("Nombre completo: "+ c.getNombreCompleto());
        System.out.println("Rut: "+ c.getRut());
        System.out.println("Usuario: "+ c.getNombreUsuario());
        System.out.println("Categoria: "+ c.getCategoriaSocio());
        System.out.println("*****************************************************");
    }

    /**
     * Funcion que verifica el acceso al menu para administrar las mesas
     * @param nombreUsuario un string con el nombre de usuario
     * @return un booleano que determina si pudo o no acceder al menu
     */
    public boolean accesoAdministrarMesas(String nombreUsuario) {
        Cliente cliente = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        if (cliente.getCategoriaSocio().equals("Platino")) {
            return true;
        } else {
            System.out.println("Acceso restringido. Solo disponible para clientes Platino.");
            return false;
        }
    }

    /**
     * Funcion que permite cambiar el estado de una mesa
     */
    public void cambiarEstadoMesa(){
        StdOut.println("Ingrese el id de la mesa que desee cambiar de estado");
        int id = StdIn.readInt();
        Mesa m = contenedorMesas.obtenerMesaPorId(id);
        String estado = m.getEstado();

        if(id < 1 || id > getCantidadActualMesas()){
            System.out.println("La id ingresada esta fuera del alcance de las listas actuales");
        }

        if(estado.equals("Disponible")){
            System.out.println("La mesa con el id "+ id + ", se cambio su estado a Cerrada ");
            m.setEstado("Cerrado");
        }
        if(estado.equals("Cerrada")){
            System.out.println("La mesa con el id "+ id + ", se cambio su estado a Disponible");
            m.setEstado("Disponible");
        }
    }

    /**
     * Funcion que verifica y permite subir de catregoria del usuario
     * @param nombreUsuario un String con el nombre de usuario
     * @param eleccion un String con la opcion que ingreso el usuario
     */
    public void subirCategoria(String nombreUsuario, String eleccion) {
        Cliente cliente = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        String rut = cliente.getRut();
    }

    /**
     * Funcion que permite cambiar la contraseña del usuario
     * @param nombreUsuario un String con el nombre de usuario
     * @param contraseniaActual un String con la contraseña actual del usuario
     * @param nuevaContrasenia un String con la nueva contraseña del usuario
     * @return un booleano que determina si se cambio o no la contraseña
     */
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

    /**
     * Funcion que despliega todas las mesas disponibles
     */
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

    /**
     * Funcion que despliega las estadisticas del sistema
     */
    public void desplegarEstadistica(){
        // Cantidad de jugadas realizadas dentro del sistema
        System.out.println("La cantidad de Jugadas realizadas dentro del sistema actualmente es igual a: "+ contenedorJugadas.getCantidadActualjugadas());
        // Mesa mas frecuentada

        // Cliente con mayor balance positivo

        // Porcentaje global de juegop sobre el total de juegos
        int contadorJugadasGanadas = 0;
        int cantidadActual = contenedorJugadas.getCantidadActualjugadas();
        for (int i = 0; i < contenedorJugadas.getCantidadActualjugadas(); i++) {
            if(contenedorJugadas.getJugadas(i).getResultado().equals("Ganada")){
                contadorJugadasGanadas++;
            }
        }
        System.out.println("El porcentaje de jugadas ganadas respecto a las totales es de: "+ (contadorJugadasGanadas*100)/cantidadActual +"%");
    }

    /**
     * Funcion que carga la informacion del sistema dentro de los archivos txt
     */
    public void subirDatos(){

    }

}
