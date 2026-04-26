//Para leer los documentos txt
import ucn.*;

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
public class SistemaCasinoImpl implements SistemaCasino {
    private ContenedorClientes contenedorClientes;
    private ContenedorMesas contenedorMesas;
    private ContenedorJugadas contenedorJugadas;

    /**
     * Función que crea inicializa el sistema
     * @param cantMaxMesa     un arreglo con la cantidad de mesas
     * @param cantMaxJugada   un arreglo con la cantidad de jugadas
     * @param cantMaxClientes un arreglo con la cantidad de clientes
     */
    public SistemaCasinoImpl(int cantMaxMesa, int cantMaxJugada, int cantMaxClientes) {
        this.contenedorClientes = new ContenedorClientes(cantMaxClientes);
        this.contenedorJugadas = new ContenedorJugadas(cantMaxJugada);
        this.contenedorMesas = new ContenedorMesas(cantMaxMesa);
    }

    /**
     * Función que carga los datos de los archivos txt jugadas, clientes, mesas.
     * @throws IOException
     */
    @Override
    public void cargaDeDatos() throws IOException {
        ArchivoEntrada archivoEntradaClientes = new ArchivoEntrada("clientes.txt");
        while (!archivoEntradaClientes.isEndFile()) {

            Registro regEnt = archivoEntradaClientes.getRegistro();
            String rut = regEnt.getString();
            String nombre = regEnt.getString();
            String apellidoPaterno = regEnt.getString();
            String apellidoMaterno = regEnt.getString();
            String nombreUsuario = regEnt.getString();
            String contrasenia = regEnt.getString();
            String categoria = regEnt.getString();
            try{
                ingresarCliente(rut, nombre, apellidoPaterno, apellidoMaterno, nombreUsuario, contrasenia, categoria);
            } catch (Exception e) {
                System.out.println("Error: Los clientes no se han registrado correctamente.");
                break;
            }
        }

        ArchivoEntrada archivoEntradaMesas = new ArchivoEntrada("mesas.txt");
        while (!archivoEntradaMesas.isEndFile()) {

            Registro regEnt = archivoEntradaMesas.getRegistro();
            int id = regEnt.getInt();
            String tipoJuego = regEnt.getString();
            String descripcion = regEnt.getString();
            int montoMin = regEnt.getInt();
            int montoMax = regEnt.getInt();
            String estado = regEnt.getString();
            try{
                ingresarMesa(id, tipoJuego, descripcion, montoMin, montoMax, estado);
            } catch (Exception e) {
                System.out.println("Error: Las mesas no se han registrado correctamente.");
                break;
            }
        }

        ArchivoEntrada archivoEntradaJugadas = new ArchivoEntrada("jugadas.txt");
        while (!archivoEntradaJugadas.isEndFile()) {

            Registro regEnt = archivoEntradaJugadas.getRegistro();
            String rutCliente = regEnt.getString();
            int idMesa = regEnt.getInt();
            String fecha = regEnt.getString();
            int monto = regEnt.getInt();
            String resultado = regEnt.getString();
            Mesa mesa = contenedorMesas.obtenerMesaPorId(idMesa);
            Cliente cliente = contenedorClientes.buscarClientePorRut(rutCliente);
            try{
                ingresarJugada(cliente, mesa, fecha, monto, resultado);
            } catch (Exception e) {
                System.out.println("Error: Las jugadas no se han registrado correctamente.");
                break;
            }
        }
    }

    /**
     * Función que permite ingresar un cliente
     * @param rut             un String con el rut del cliente
     * @param nombre          un String con el nombre del cliente
     * @param apellidoPaterno un String con el apellido paterno del cliente
     * @param apellidoMaterno un String con el apellido materno del cliente
     * @param nombreUsuario   un String con el nombre de usuario del cliente
     * @param contrasenia     un String con la contraseña del cliente
     * @param categoriaSocio  un String con la categoria del cliente
     * @return un booleano que determina si se pudo o no ingresar el cliente
     */
    @Override
    public boolean ingresarCliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String contrasenia, String categoriaSocio) {
        Cliente cliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, nombreUsuario, contrasenia, categoriaSocio);
        return this.contenedorClientes.agregarCliente(cliente);
    }

    /**
     * Función que permite ingresar una mesa
     * @param id          un entero con el id de la mesa
     * @param tipoJuego   un String con el tipo de juego de la mesa
     * @param descripcion un String con la descripción de la mesa
     * @param apuestaMin  un entero con la apuesta minima de la mesa
     * @param apuestaMax  un entero con la apuesta maxima de la mesa
     * @param estado      un String con el estado de la mesa
     * @return un booleano que determina si se pudo o no ingresar la mesa
     */
    @Override
    public boolean ingresarMesa(int id, String tipoJuego, String descripcion, int apuestaMin, int apuestaMax, String estado) {
        Mesa mesa = new Mesa(id, tipoJuego, descripcion, apuestaMin, apuestaMax, estado);
        return this.contenedorMesas.agregarMesa(mesa);
    }

    /**
     * Función que permite ingresar una jugada
     * @param cliente   un objeto con el rut del cliente
     * @param mesa      un objeto de tipo Mesa
     * @param fecha     un String con la fecha de la jugada
     * @param apuesta   un entero con la apuesta realizada
     * @param resultado un String con el resultado de la jugada
     * @return un booleano que determina si se pudo o no ingresar la jugada
     */
    @Override
    public boolean ingresarJugada(Cliente cliente, Mesa mesa, String fecha, int apuesta, String resultado) {
        Jugada jugada = new Jugada(cliente, mesa, fecha, apuesta, resultado);
        return this.contenedorJugadas.agregarJugada(jugada);
    }

    /**
     * Función que obtiene la cantidad actual de mesas
     * @return un entero con la cantidad actual de mesas
     */
    public int getCantidadActualMesas() {
        return contenedorMesas.getCantidadActual();
    }

    /**
     * Este metodo permite registrar un nuevo cliente al sistema
     */
    public void registroNuevoCliente() {
        StdOut.println("------- Registrar Cliente --------");
        StdOut.println("Ingrese el nombre del nuevo cliente: ");
        String nombre = StdIn.readString();
        StdOut.println("Ingrese el apellido paterno del nuevo cliente: ");
        String apellidoPaterno = StdIn.readString();
        StdOut.println("Ingrese el apellido materno del nuevo cliente: ");
        String apellidoMaterno = StdIn.readString();
        StdOut.println("Ingrese el RUT del nuevo cliente (En formato: 12.345.678-9): ");
        String rutCliente = StdIn.readString();
        StdOut.println("Ingrese el nombre de usuario del nuevo cliente: ");
        String nombreUsuario = StdIn.readString();

        String rutSinVerificador = rutCliente.replaceAll("[^0-9]", "");
        String contrasenia = rutSinVerificador.substring(0, rutSinVerificador.length() - 1);

        ingresarCliente(rutCliente, nombre, apellidoPaterno, apellidoMaterno, nombreUsuario, contrasenia, "Normal");
        System.out.print("El cliente ha sido registrado exitosamente.\n");
    }

    /**
     * Función que verifica los datos ingresados para iniciar sesión
     * @param nombreUsuario un String con el nombre de usuario
     * @param contrasenia   un String con la contraseña del usuario
     * @return un booleano que determina si se pudo o no iniciar sesión
     */
    @Override
    public boolean iniciarSesion(String nombreUsuario, String contrasenia) {
        if (contenedorClientes.buscarClientePorNombre(nombreUsuario) != null) {
            if (contenedorClientes.buscarClientePorNombre(nombreUsuario).getContrasenia().equals(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Función que despliega las mesas con el estado "Disponible"
     */
    @Override
    public void desplegarMesaDisponible() {
        for (int i = 0; i < this.contenedorMesas.getCantidadActual(); i++) {
            Mesa m = this.contenedorMesas.obtenerMesa(i);
            if (m.getEstado().equals("Disponible")) {
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
     * Función que verifica y registra la jugada del cliente en una mesa
     * @param nombreUsuario un String con el nombre de usuario
     * @param jugadasHoy    la cantidad de jugadas realizadas por hoy
     */
    @Override
    public void registrarSesionJuego(String nombreUsuario, int jugadasHoy) {
        Cliente cliente = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        if (!contenedorClientes.puedeJugar(cliente.getCategoriaSocio(), jugadasHoy)) {
            System.out.println("Ya no puede seguir jugando, ya alcanzó su limite de jugadas por hoy.");
        } else {
            desplegarMesaDisponible();
            StdOut.println("Ingrese el id de la mesa en la que desea apostar: ");
            int id = StdIn.readInt();
            StdOut.println("Ingrese el monto de su apuesta: ");
            int monto = StdIn.readInt();
            Mesa m = this.contenedorMesas.obtenerMesaPorId(id);

            if (m == null || !m.getEstado().equals("Disponible")) {
                System.out.println("No hay mesas disponibles para jugar.");
                return;
            }
            if (monto < m.getApuestaMin() || monto > m.getApuestaMax()) {
                System.out.println("El monto ingresado no entra en el rango de la mesa.");
                return;
            }
            LocalDateTime fechaActual = LocalDateTime.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha = fechaActual.format(formatoFecha);

            Random random = new Random();
            double suerte = random.nextDouble();
            String resultado = "";

            if (suerte < 0.45) {
                resultado = "Ganada";
                System.out.println("\nFelicidades! la jugada ha sido: " + resultado);
            } else {
                resultado = "Perdida";
                System.out.println("\nLastima, la jugada ha sido: " + resultado);
            }

            System.out.println("Creando comprobante de la sesión...");
            Jugada j = new Jugada(cliente, m, fecha, monto, resultado);
            this.contenedorJugadas.agregarJugada(j);
            comprobanteSesionJuego(cliente, j);
        }
    }

    /**
     * Función que genera el comprobante de la jugada realizada por el cliente
     * @param cliente un objeto de tipo Cliente
     * @param j       un objeto de tipo Jugada
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
     * Función que genera todos los comprobantes de las jugadas asociadas a un cliente por rut
     * @param nombreUsuario un String con el nombre de usuario
     */
    @Override
    public void consultarHistorial(String nombreUsuario) {
        Cliente cliente = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        int balance = 0;
        contenedorJugadas.ordenarJugadas();

        for (int i = 0; i < contenedorJugadas.getCantidadActualjugadas(); i++) {
            Jugada j = contenedorJugadas.obtenerJugadas(i);
            if (j.getCliente().getRut().equals(cliente.getRut())) {
                comprobanteSesionJuego(cliente, j);
                if (j.getResultado().equals("Ganada")) {
                    balance += j.getApuesta();
                } else {
                    balance -= j.getApuesta();
                }
            }
        }
        System.out.println("Balance total: " + balance);
    }

    /**
     * Función que permite ver los datos personales del usuario
     * @param nombreUsuario un String con el nombre de usuario
     */
    public void verDatosPersonales(String nombreUsuario) {
        Cliente c = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        System.out.println("*****************************************************");
        System.out.println("                  DATOS PERSONALES");
        System.out.println("Nombre completo: " + c.getNombreCompleto());
        System.out.println("Rut: " + c.getRut());
        System.out.println("Usuario: " + c.getNombreUsuario());
        System.out.println("Categoria: " + c.getCategoriaSocio());
        System.out.println("*****************************************************");
    }

    /**
     * Función que verifica el acceso al menu para administrar las mesas
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
     * Función que permite cambiar el estado de una mesa
     */
    public void cambiarEstadoMesa() {
        StdOut.println("Ingrese el id de la mesa que desee cambiar de estado: ");
        int id = StdIn.readInt();
        if (id < 1 || id > getCantidadActualMesas()) {
            System.out.println("La id ingresada esta fuera del alcance de las listas actuales");
        } else {
            Mesa m = contenedorMesas.obtenerMesaPorId(id);
            String estado = m.getEstado();

            if (estado.equals("Disponible")) {
                System.out.println("La mesa con el id " + id + ", se cambio su estado a Cerrada ");
                m.setEstado("Cerrado");
            }
            if (estado.equals("Cerrada")) {
                System.out.println("La mesa con el id " + id + ", se cambio su estado a Disponible");
                m.setEstado("Disponible");
            }
        }
    }

    /**
     * Función que verifica y permite subir de categoría del usuario
     * @param nombreUsuario un String con el nombre de usuario
     */
    public void subirCategoria(String nombreUsuario) {
        Cliente cliente = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        String rut = cliente.getRut();
        String categoriaActual = cliente.getCategoriaSocio();
        int partidasGanadas = contenedorJugadas.contarPartidasGanadas(rut, categoriaActual);

        System.out.println("=== Subir de Categoría ===");
        System.out.println("Categoría actual : " + categoriaActual);
        System.out.println("Partidas ganadas : " + partidasGanadas);
        System.out.println("¿Deseas subir de categoría? (Si/No)");
        String respuesta = StdIn.readString();
        if (respuesta.equalsIgnoreCase("si")) {
            if (contenedorClientes.puedeSubirCategoria(categoriaActual, partidasGanadas)) {
                String siguienteCategoria = contenedorClientes.categoriaSiguiente(categoriaActual);
                cliente.setCategoriaSocio(siguienteCategoria);
                System.out.println("Su categoría ha sido ascendida a "+ siguienteCategoria + " exitosamente.");
            } else {
                if (categoriaActual.equals("Platino")) {
                    System.out.println("Ya tienes la categoría máxima.");
                } else {
                    System.out.println("Aún no cumples los requisitos para subir de categoría.");
                }
            }
        } else {
            System.out.println("Mantuviste tu categoría actual: " +  categoriaActual);
        }
    }

    /**
     * Funcion que permite cambiar la contraseña del usuario
     * @param nombreUsuario     un String con el nombre de usuario
     * @param contraseniaActual un String con la contraseña actual del usuario
     * @return un booleano que determina si se cambio o no la contraseña
     */
    public boolean cambiarContrasenia(String nombreUsuario, String contraseniaActual) {
        //Obtenemos el cliente
        Cliente c = contenedorClientes.buscarClientePorNombre(nombreUsuario);
        StdOut.println("Ingrese la nueva contraseña: ");
        String nuevaContrasenia = StdIn.readString();
        //Comparamos la contraseña ingresada con la actual
        if (!nuevaContrasenia.equals(c.getContrasenia())) {
            //Analisis de la nueva contraseña
            if (nuevaContrasenia.length() >= 8) {

                boolean tieneMayus = false;
                boolean tieneNumero = false;

                for (char texto : nuevaContrasenia.toCharArray()) {
                    if (Character.isUpperCase(texto)) tieneMayus = true;
                    if (Character.isDigit(texto)) tieneNumero = true;
                }
                //Se establece la nueva contraseña con exito
                if (tieneMayus && tieneNumero) {
                    System.out.println("Se cambio la contraseña con éxito");
                    c.setContrasenia(nuevaContrasenia);
                    return true;
                } else if (!tieneMayus) {
                    System.out.println("La contraseña debe de contener al menos una letra mayúscula. Inténtelo nuevamente");
                }else{
                    System.out.println("La contraseña debe de contener al menos un numero. Inténtelo nuevamente.");
                    return false;
                }
            }
            System.out.println("La contraseña debe tener al menos 8 caracteres. Inténtelo nuevamente.");
            return false;
        }
        System.out.println("La contraseña debe ser distinta a la contraseña actual. Inténtelo nuevamente");
        return false;
    }

    /**
     * Función que despliega todas las mesas disponibles
     */
    public void desplegarMesas() {
        for (int i = 0; i < this.contenedorMesas.getCantidadActual(); i++) {
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
     * Este metodo permite al usuario crear y agregar una mesa de juego al sistema.
     */
    public void crearYAgregarMesa(){
        do {
            StdOut.println("------- Crear Mesa --------");
            StdOut.println("Ingrese el tipo de juego de la mesa: ");
            String tipo = StdIn.readString();
            StdOut.println("Ingrese la descripción de la mesa: ");
            String descripcion = StdIn.readString();
            StdOut.println("Ingresa la apuesta minima de la mesa: ");
            int apuestaMin = StdIn.readInt();
            if (apuestaMin < 0) {
                System.out.println("La apuesta minima debe ser mayor a 0.");
            } else {
                StdOut.println("Ingresa la apuesta maxima de la mesa: ");
                int apuestaMax = StdIn.readInt();
                if (apuestaMax < apuestaMin) {
                    System.out.println("La apuesta maxima debe de ser mayor que la apuesta minima.");
                    break;
                } else if (apuestaMax <= 0) {
                    System.out.println("La apuesta máxima debe de ser mayor a 0.");
                } else {
                    StdOut.println("Ingrese el estado de la mesa (Disponible/Cerrada): ");
                    String estado = StdIn.readString();
                    int idMesa = contenedorMesas.getCantidadActual() + 1;
                    ingresarMesa(idMesa, tipo, descripcion, apuestaMin, apuestaMax, estado);
                    System.out.println("La mesa ha sido creada y agregada al sistema exitosamente.");
                }
            }
        }while (false);
    }

    /**
     * Función que despliega las estadísticas del sistema
     */
    public void desplegarEstadistica() {
        // Cantidad de jugadas realizadas dentro del sistema
        System.out.println("La cantidad de Jugadas realizadas dentro del sistema actualmente es igual a: " + contenedorJugadas.getCantidadActualjugadas());

        // Mesa mas frecuentada
        int[] conteoMesas = new int[contenedorJugadas.getCantidadActualjugadas()];
        for (int i = 0; i < contenedorJugadas.getCantidadActualjugadas(); i++) {
            int idMesa = contenedorJugadas.obtenerJugadas(i).getMesa().getId();
            for (int j = 0; j < contenedorMesas.getCantidadActual(); j++) {
                if (contenedorMesas.obtenerMesa(j).getId() == idMesa) {
                    conteoMesas[j]++;
                    break;
                }
            }
        }
        int maximo = 0;
        for (int i = 0; i < conteoMesas.length; i++) {
            if (conteoMesas[i] > maximo) {
                maximo = conteoMesas[i];
            }
        }
        System.out.println("=== Mesa(s) más frecuentada(s) ===");
        for (int i = 0; i < contenedorMesas.getCantidadActual(); i++) {
            if (conteoMesas[i] == maximo) {
                Mesa m = contenedorMesas.obtenerMesa(i);
                System.out.println("Juego: " + m.getTipoJuego() + " | ID: " + m.getId() + " | Jugadas: " + maximo);
            }
        }

        // Cliente con mayor balance positivo
        int[] balances = new int[contenedorClientes.getCantActualCliente()];
        for (int i = 0; i < contenedorJugadas.getCantidadActualjugadas(); i++) {
            Jugada j = contenedorJugadas.obtenerJugadas(i);
            String rutCliente = j.getCliente().getRut();
            for (int k = 0; k < contenedorClientes.getCantActualCliente(); k++) {
                if (contenedorClientes.obtenerCliente(k).getRut().equals(rutCliente)) {
                    if (j.getResultado().equals("Ganada")) {
                        balances[k] += j.getApuesta();
                    } else {
                        balances[k] -= j.getApuesta();
                    }
                    break;
                }
            }
        }
        int maxBalance = 0;
        for (int i = 0; i < balances.length; i++) {
            if (balances[i] > maxBalance) {
                maxBalance = balances[i];
            }
        }
        System.out.println("=== Cliente(s) con mayor balance positivo ===");
        if (maxBalance == 0) {
            System.out.println("Ningún cliente tiene balance positivo.");
        }else {
            for (int i = 0; i < contenedorMesas.getCantidadActual(); i++) {
                if (conteoMesas[i] == maximo) {
                    Cliente c = contenedorClientes.obtenerCliente(i);
                    StdOut.println("--------------------");
                    System.out.println("Nombre completo: " + c.getNombreCompleto());
                    System.out.println("Usuario: " + c.getNombreUsuario());
                    System.out.println("Balance: $" + maxBalance);
                    StdOut.println("--------------------");
                }
            }
        }

        // Porcentaje global de juego sobre el total de juegos
        int contadorJugadasGanadas = 0;
        int cantidadActual = contenedorJugadas.getCantidadActualjugadas();
        for (int i = 0; i < contenedorJugadas.getCantidadActualjugadas(); i++) {
            if (contenedorJugadas.obtenerJugadas(i).getResultado().equals("Ganada")) {
                contadorJugadasGanadas++;
            }
        }
        System.out.println("El porcentaje de jugadas ganadas respecto a las totales es de: " + (contadorJugadasGanadas * 100) / cantidadActual + "%");
    }

    /**
     * Función que carga la información del sistema dentro de los archivos txt
     */
    public void subirDatos() throws IOException {
        ArchivoSalida archivoSalidaClientes = new ArchivoSalida("clientes.txt");
        for(Cliente cliente : contenedorClientes.getContenedorClientes()){
            if (cliente != null){
                Registro regSal = new Registro(7);

                regSal.agregarCampo(cliente.getRut());
                regSal.agregarCampo(cliente.getNombre());
                regSal.agregarCampo(cliente.getApellidoPaterno());
                regSal.agregarCampo(cliente.getApellidoMaterno());
                regSal.agregarCampo(cliente.getNombreUsuario());
                regSal.agregarCampo(cliente.getContrasenia());
                regSal.agregarCampo(cliente.getCategoriaSocio());

                archivoSalidaClientes.writeRegistro(regSal);
            }
        }

        ArchivoSalida archivoSalidaMesas = new ArchivoSalida("mesas.txt");
        for(Mesa mesa : contenedorMesas.getContenedorMesas()){
            if (mesa != null){
                Registro regSal = new Registro(6);

                regSal.agregarCampo(mesa.getId());
                regSal.agregarCampo(mesa.getTipoJuego());
                regSal.agregarCampo(mesa.getDescripcion());
                regSal.agregarCampo(mesa.getApuestaMin());
                regSal.agregarCampo(mesa.getApuestaMax());
                regSal.agregarCampo(mesa.getEstado());

                archivoSalidaMesas.writeRegistro(regSal);
            }
        }

        ArchivoSalida archivoSalidaJugadas = new ArchivoSalida("jugadas.txt");
        for(Jugada jugada : contenedorJugadas.getContenedorJugadas()) {
            if (jugada != null) {
                Registro regSal = new Registro(5);

                regSal.agregarCampo(jugada.getCliente().getRut());
                regSal.agregarCampo(jugada.getMesa().getId());
                regSal.agregarCampo(jugada.getFechaJugada());
                regSal.agregarCampo(jugada.getApuesta());
                regSal.agregarCampo(jugada.getResultado());

                archivoSalidaJugadas.writeRegistro(regSal);
            }
        }
        archivoSalidaMesas.close();
        archivoSalidaJugadas.close();
        archivoSalidaClientes.close();
    }
}