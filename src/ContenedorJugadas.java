/**
 * Representa la identidad ContenedorJugadas
 */
public class ContenedorJugadas {
    /**
     * Contenedor de las jugadas
     */
    private Jugada[] jugadas;
    /**
     * Cantidad actual de Jugadas
     */
    private int cantActualJugada;
    /**
     * Cantidad máximas de Jugadas
     */
    private int cantMaxJugada;

    /**
     * Constructor de la clase ContenedorJugada
     * @param cantMaxJugada Es la cantidad maxima del contenedor
     */
    public ContenedorJugadas(int cantMaxJugada){
        this.cantMaxJugada = cantMaxJugada;
        this.cantActualJugada = 0;
        this.jugadas = new Jugada[cantMaxJugada];
    }

    /**
     * Agrega una jugada al ContenedorJugadas
     * @param jugada un objeto de Clase Jugada
     * @return un booleano con la confirmación del agregado
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
     * Obtiene la cantidad actual de jugadas en el ContenedorJugadas
     * @return un entero con la cantidad actual de jugadas
     */
    public int getCantidadActualjugadas(){
        return this.cantActualJugada;
    }

    /**
     * Función que ordena las jugadas por fecha de más reciente a más antigua
     */
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

    /**
     * Obtiene una jugada por su posición
     * @param posicion un entero que representa la posición
     * @return un objeto Jugada
     */
    public Jugada obtenerJugadas(int posicion) {
        return jugadas[posicion];
    }

    /**
     * Obtiene las jugadas de su contenedor
     * @return un objeto Jugada
     */
    public Jugada[] getContenedorJugadas(){
        return this.jugadas;
    }

    /**
     * Este metodo cuenta las partidas ganadas por el cliente
     * @param rutCliente es el rut del cliente
     * @param categoriaActual es la categoria en la que se encuentra el cliente
     * @return un contador de tipo integer
     */
    public int contarPartidasGanadas(String rutCliente, String categoriaActual){
        int contador = 0;
        for (int i = 0; i < this.cantActualJugada; i++) {
            Jugada j = jugadas[i];
            if(j.getCliente().getRut().equals(rutCliente) && j.getResultado().equals("Ganada")
                    && j.getCategoriaEnJugada().equals(categoriaActual)){
                contador++;
            }
        }
        return contador;
    }
}
