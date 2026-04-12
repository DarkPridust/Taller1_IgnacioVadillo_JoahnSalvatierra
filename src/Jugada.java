/**
 *Representa a la entidad Jugada
 */
public class Jugada {
    /**
     * fecha registrada de la jugada
     */
    private String fecha;
    /**
     * apuesta realizada
     */
    private int apuesta;
    /**
     * resultado de la jugada
     */
    private String resultado;
    /**
     * Cliente asociado a la jugada
     */
    private Cliente cliente;
    /**
     * Mesa asociada a la jugada
     */
    private Mesa mesa;

    /**
     * Contructor de la clase jugada
     * @param fecha
     * @param apuesta
     * @param resultado
     */
    public Jugada(String fecha, int apuesta, String resultado) {
        this.fecha = fecha;
        this.apuesta = apuesta;
        this.resultado = resultado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
