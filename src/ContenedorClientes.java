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
    public Cliente buscarCliente(String nombreUsuario){
        for(int i = 0; i < this.cantActualCliente; i++){
            Cliente act = clientes[i];

            if (act.getNombreUsuario().equals(nombreUsuario)){
                return act;
            }
        }
        return null;
    }
}
