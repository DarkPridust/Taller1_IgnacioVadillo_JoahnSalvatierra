/**
 * Representa a la entidad ContenedorMesas
 */
public class ContenedorMesas {
    /**
     * Contenedor de las Mesas
     */
    private Mesa[] mesas;
    /**
     * Cantidad maxima de Mesas
     */
    private int cantMaxMesa;
    /**
     * Cantidad actual de Mesas
     */
    private int cantActualMesa;

    /**
     * Constructor de la clase ContenedorMesas
     * @param cantMax Es la cantidad maxima del contenedor
     */
    public ContenedorMesas(int cantMax){
        this.cantMaxMesa = cantMax;
        this.cantActualMesa = 0;
        this.mesas = new Mesa[cantMax];
    }

    /**
     * Agrega una Mesa al contenedor de mesas
     * @param mesa un objeto de clase Mesa
     * @return un booleano con la confirmación del agregado
     */
    public boolean agregarMesa(Mesa mesa){
        if(this.cantActualMesa >= this.cantMaxMesa){
            return false;
        }
        this.mesas[this.cantActualMesa] = mesa;
        this.cantActualMesa++;
        return true;
    }

    /**
     * Obtiene una Mesa por la posición
     * @param posicion un entero con la posición
     * @return la Mesa en la posición
     */
    public Mesa obtenerMesa(int posicion){
        if(posicion < 0 || posicion >= this.cantActualMesa){
            return null;
        }
        return this.mesas[posicion];
    }

    /**
     * Obtiene la cantidad actual de mesas en el ContenedorMesas
     * @return un entero con la cantidad actual de mesas en en contenedor
     */
    public int getCantidadActual(){
        return this.cantActualMesa;
    }

    /**
     * Obtiene la mesa según su id
     * @param id el ID de la mesa
     * @return un objeto mesa
     */
    public Mesa obtenerMesaPorId(int id){
        for(int i = 0; i < this.cantActualMesa; i++){
            if (mesas[i].getId() == id){
                return mesas[i];
            }
        }
        return null;
    }

    /**
     * Obtiene las mesas de su contenedor
     * @return un objeto Mesa
     */
    public Mesa[] getContenedorMesas(){
        return this.mesas;
    }
}
