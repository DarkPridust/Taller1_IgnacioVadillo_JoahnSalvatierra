public class ContenedorJugadas {
    private Jugada jugadas[];
    private int cantActualJugada;
    private int cantMaxJugada;

    public ContenedorJugadas(int cantMaxJugada){
        this.cantMaxJugada = cantMaxJugada;
        this.cantActualJugada = 0;
        this.jugadas = new Jugada[cantMaxJugada];
    }
    public boolean agregarJugada(Jugada jugada){
        if(this.cantActualJugada >= this.cantMaxJugada){
            return false;
        }
        this.jugadas[this.cantActualJugada] = jugada;
        this.cantActualJugada++;
        return true;

    }
    public Jugada obtenerJugada(int posicion){
        if(posicion < 0 || posicion >= this.cantActualJugada){
            return null;
        }
        return this.jugadas[posicion];
    }
    public int getCantidadActualjugadas(){
        return this.cantActualJugada;
    }
}
