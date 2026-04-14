public class ContenedorClientes {
    private Cliente clientes[];
    private int cantMaxClientes;
    private int cantActualCliente;

    public ContenedorClientes(int cantMaxClientes){
        this.cantMaxClientes = cantMaxClientes;
        this.cantActualCliente = 0;
        this.clientes = new Cliente[cantMaxClientes];
        }
    public boolean agregarCliente(Cliente cliente){
        if(this.cantActualCliente >= cantMaxClientes){
            return false;
        }
        this.clientes[cantActualCliente] = cliente;
        this.cantActualCliente++;
        return true;
    }
    public Cliente obtenerCliente(int posicion){
        if(posicion < 0 || posicion >= this.cantActualCliente){
            return null;
        }
        return this.clientes[posicion];
    }
    public int getCantActualCliente(){
        return this.cantActualCliente;
    }
}
