/**
 *Representa la identidad ContenedorClientes
 */
public class ContenedorClientes {
    /**
     *Contenedor de los clientes
     */
    private Cliente clientes[];
    /**
     *La cantidad maxima de clientes
     */
    private int cantMaxClientes;
    /**
     *La cantidad actual de clientes
     */
    private int cantActualCliente;

    /**
     * Constructor de la clase ContenedorClientes
     * @param cantMaxClientes
     */
    public ContenedorClientes(int cantMaxClientes){
        this.cantMaxClientes = cantMaxClientes;
        this.cantActualCliente = 0;
        this.clientes = new Cliente[cantMaxClientes];
        }

    /**
     * agrega un cliente al ContenedorCliente
     * @param cliente un objeto de clae Cliente
     * @return un booleano con la confirmacion de agregado
     */
    public boolean agregarCliente(Cliente cliente){
        if(this.cantActualCliente >= cantMaxClientes){
            System.err.println("No se puede agregar más empleados. Capacidad maxima alcanzada.");
            return false;
        }
        if (this.buscarClientePorRut(cliente.getRut()) != null){
            System.err.println("Ya existe un cliente con el RUT: " + cliente.getRut());
            return false;
        }
        if (this.buscarClientePorNombre(cliente.getNombreUsuario()) != null){
            System.err.println("Ya existe un cliente con el nombre de usuario: " + cliente.getNombreUsuario());
            return false;
        }
        this.clientes[cantActualCliente] = cliente;
        this.cantActualCliente++;
        return true;
    }

    /**
     * Obtiene un cliente por posicion
     * @param posicion un entero con la posicion
     * @return un objeto de clase Cliente
     */
    public Cliente obtenerCliente(int posicion){
        if(posicion < 0 || posicion >= this.cantActualCliente){
            return null;
        }
        return this.clientes[posicion];
    }

    /**
     * obtiene la cantidad actual de clientes en el ContenedorClientes
     * @return un entero con la cantidad actual de clientes
     */
    public int getCantActualCliente(){
        return this.cantActualCliente;
    }

    /**
     * Busca un cliente por su nombre de usuario
     * @param nombreUsuario un String con el nombre del usuario
     * @return un objeto de clase cliente
     */
    public Cliente buscarClientePorNombre(String nombreUsuario){
        for(int i = 0; i < this.cantActualCliente; i++){
            if (this.clientes[i].getNombreUsuario().equals(nombreUsuario)){
                return this.clientes[i];
            }
        }
        return null;
    }

    /**
     * Busca un cliente por su rut
     * @param rut un String con el RUT del usuario
     * @return un objeto de clase cliente
     */
    public Cliente buscarClientePorRut(String rut){
        for(int i = 0; i < this.cantActualCliente; i++){
            if (this.clientes[i].getRut().equals(rut)){
                return this.clientes[i];
            }
        }
        return null;
    }

    public boolean puedeJugar(String nivelSocio, int jugadasHoy){
        if(nivelSocio.equals("Normal") || nivelSocio.equals("Plata")){
            if(jugadasHoy == 1){
                return false;
            } else {
                return true;
            }
        }else if(nivelSocio.equals("Oro")){
            if (jugadasHoy == 2){
                return false;
            } else {
                return true;
            }
        }else if(nivelSocio.equals("Platino")){
            return true;
        }else{
            return false;
        }
    }

    public boolean puedeSubirCategoria(String categoriaActual,int sesionesGanadas) {
        switch (categoriaActual) {
            case "Normal":
                return sesionesGanadas >= 5;
            case "Plata":
                return sesionesGanadas >= 10;
            case "Oro":
                return sesionesGanadas >= 20;
            default:
                return false;
        }
    }

    public String categoriaSiguiente(String categoriaActual) {
        switch (categoriaActual) {
            case "Normal":
                return "Plata";
            case "Plata":
                return "Oro";
            case "Oro":
                return "Platino";
            default:
                return "Platino";
        }
    }
    public Cliente[] getContenedorClientes(){
        return this.clientes;
    }
}
