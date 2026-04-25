import java.time.LocalDateTime;

/**
 *Representa a la entidad Jugada
 */
public class Jugada {
    /**
     * rut del cliente asociado
     */
    private Cliente cliente;
    /**
     * fecha de hoy
     */
    private LocalDateTime fechaHoy;
    /**
     * fecha registrada de la jugada
     */
    private String fechaJugada;
    /**
     * apuesta realizada
     */
    private int apuesta;
    /**
     * resultado de la jugada
     */
    private String resultado;
    /**
     * Mesa asociada a la jugada
     */
    private Mesa mesa;

    public Jugada(Cliente cliente, Mesa mesa, String fechaJugada, int apuesta, String resultado) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.fechaJugada = fechaJugada;
        this.apuesta = apuesta;
        this.resultado = resultado;
        this.fechaHoy = LocalDateTime.now();
    }

    /**
     * obtiene el cliente asociado
     * @return un objeto de tipo Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente asociado
     * @param cliente un objeto de tipo Cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * obtiene la mesa asociada
     * @return Un objeto Mesa que contiene la mesa asociada
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Obtiene la fecha de la jugada
     * @return un String con la fecha de la jugada
     */
    public String getFechaJugada() {
        return fechaJugada;
    }

    /**
     * Establece la fecha de la jugada
     * * <p> La fecha de la jugada debe de ser exactamente el dia en el que se realizo</p>
     * @param fechaJugada un String con la fecha de la jugada
     */
    public void setFecha(String fechaJugada) {
        this.fechaJugada = fechaJugada;
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

    public LocalDateTime getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(LocalDateTime fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

}
