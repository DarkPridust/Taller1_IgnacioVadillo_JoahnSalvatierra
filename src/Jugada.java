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

    /**
     * Obtiene
     * @return
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece
     * @param fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene
     * @return
     */
    public int getApuesta() {
        return apuesta;
    }

    /**
     * Establece
     * @param apuesta
     */
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    /**
     * Obtiene
     * @return
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Establece
     * @param resultado
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
