public class ContenedorMesas {
    private Mesa mesas[];
    private int cantMaxMesa;
    private int cantActualMesa;

    public ContenedorMesas(int cantMax){
        this.cantMaxMesa = cantMax;
        this.cantActualMesa = 0;
        this.mesas = new Mesa[cantMax];
    }

    public boolean agregarMesa(Mesa mesa){
        if(this.cantActualMesa >= this.cantMaxMesa){
            return false;
        }
        this.mesas[this.cantActualMesa] = mesa;
        this.cantActualMesa++;
        return true;
    }

    public Mesa obtenerMesa(int posicion){
        if(posicion < 0 || posicion >= this.cantActualMesa){
            return null;
        }
        return this.mesas[posicion];
    }
    public int getCantidadActual(){ return this.cantActualMesa;
    }
}
