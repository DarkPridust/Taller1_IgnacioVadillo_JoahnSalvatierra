/**
 *Representa a la entidad Jugada
 */
public class Jugada {
    /**
     * rut del cliente asociado
     */
    private String rutCliente;
    /**
     * id de la mesa asociada
     */
    private int idMesa;
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

    public Jugada(String rutCliente, int idMesa, String fecha, int apuesta, String resultado) {
        this.rutCliente = rutCliente;
        this.idMesa = idMesa;
        this.fecha = fecha;
        this.apuesta = apuesta;
        this.resultado = resultado;
    }

    /**
     * Obtiene la fecha de la jugada
     * @return un String con la fecha de la jugada
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la jugada
     * * <p> La fecha de la jugada debe de ser exactamente el dia en el que se realizo</p>
     * @param fecha un String con la fecha de la jugada
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la  apuesta de la jugada
     * @return un entero con la apuesta de la jugada
     */
    public int getApuesta() {
        return apuesta;
    }

    /**
     * Establece la  apuesta de la jugada
     * * <p> La apuesta debe de estar en el rango entre la apuesta minima y apuesta maxima de la mesa</p>
     * @param apuesta un entero con la apuesta de la jugada
     */
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    /**
     * Obtiene el resultado de la jugada
     * @return un String con el resultado de la jugada
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Establece el resultado de la jugada
     * * <p> los unicos resultados posibles son Ganado y Perdido</p>
     * @param resultado un String con el resultado de la jugada
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
