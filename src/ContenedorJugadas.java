import java.time.LocalDateTime;

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

    public int contarJugadas(String rutCliente){
        LocalDateTime hoy = LocalDateTime.now();
        int contador = 0;
        for(int i = 0; i < this.cantActualJugada; i++){
            if(jugadas[i].getRutCliente().equals(rutCliente) && jugadas[i].getFechaHoy().toLocalDate().equals(hoy)){
                contador++;
            }
        }
        return contador;
    }

    public void ordenarJugadas(){
        for (int i = 0; i < this.cantActualJugada - 1; i++) {
            for (int j = 0; j < this.cantActualJugada - 1 - i; j++) {
                if (jugadas[j].getFechaHoy().isBefore(jugadas[j + 1].getFechaHoy())) {
                    Jugada temp = jugadas[j];
                    jugadas[j] = jugadas[j + 1];
                    jugadas[j + 1] = temp;
                }
            }
        }
    }

    public Jugada getJugadas(int i) {
        return jugadas[i];
    }
}
