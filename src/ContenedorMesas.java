/**
 * Representa a la entidad ContenedorMesas
 */
public class ContenedorMesas {
    /**
     * Contenedor de las Mesas
     */
    private Mesa mesas[];
    /**
     * Cantidad maxima de Mesas
     */
    private int cantMaxMesa;
    /**
     * Cantidad actual de Mesas
     */
    private int cantActualMesa;

    /**
     * constructor de la clase ContenedorMesas
     * @param cantMax
     */
    public ContenedorMesas(int cantMax){
        this.cantMaxMesa = cantMax;
        this.cantActualMesa = 0;
        this.mesas = new Mesa[cantMax];
    }

    /**
     * Agreaga una Mesa al contenedor de mesas
     * @param mesa un objeto de clase Mesa
     * @return un booleano con la confirmacion del agregado
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
     * Obtiene una Mesa por la posicion
     * @param posicion un entero con la posicion
     * @return la Mesa en la posicion
     */

    public Mesa obtenerMesa(int posicion){
        if(posicion < 0 || posicion >= this.cantActualMesa){
            return null;
        }
        return this.mesas[posicion];
    }

    /**
     * obtiene la cantidad actual de mesas en el ContenedorMesas
     * @return un entero con la cantidad actual de mesas en en contenedor
     */
    public int getCantidadActual(){ return this.cantActualMesa;
    }

    public Mesa obtenerMesaPorId(int id){
        for(int i = 0; i < this.cantActualMesa; i++){
            Mesa m = mesas[i];

            if (m.getId() == id){
                return m;
            }
        }
        return null;
    }

}
