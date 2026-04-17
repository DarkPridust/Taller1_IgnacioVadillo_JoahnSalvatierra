/**
 * Representa la identidad ContenedorJugagadas
 */
public class ContenedorJugadas {
    /**
     * Contenedor de las jugadas
     */
    private Jugada jugadas[];
    /**
     * Cantidad maxima de Jugadas
     */
    private int cantActualJugada;
    /**
     * Cantidad actual de Jugadas
     */
    private int cantMaxJugada;

    /**
     * Constructo de la clase ContenedorJugada
     * @param cantMaxJugada
     */
    public ContenedorJugadas(int cantMaxJugada){
        this.cantMaxJugada = cantMaxJugada;
        this.cantActualJugada = 0;
        this.jugadas = new Jugada[cantMaxJugada];
    }

    /**
     * Agrega una jugada al ContenedorJugadas
     * @param jugada un objeto de Clase Jugada
     * @return un booleano con la confirmacion del agregado
     */
    public boolean agregarJugada(Jugada jugada){
        if(this.cantActualJugada >= this.cantMaxJugada){
            return false;
        }
        this.jugadas[this.cantActualJugada] = jugada;
        this.cantActualJugada++;
        return true;
    }

    /**
     * Obtiene una jugada por la posicion
     * @param posicion un entero con la posicion
     * @return un objeto de clase Jugada
     */
    public Jugada obtenerJugada(int posicion){
        if(posicion < 0 || posicion >= this.cantActualJugada){
            return null;
        }
        return this.jugadas[posicion];
    }

    /**
     * Obtiene la cantidad actual de jugadas en el ContenedorJugadas
     * @return un entero con la cantidad actual de jugadas
     */
    public int getCantidadActualjugadas(){
        return this.cantActualJugada;
    }
}
