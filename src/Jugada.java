import java.time.LocalDateTime;

/**
 * Representa a la entidad Jugada
 */
public class Jugada {
    /**
     * Rut del cliente asociado
     */
    private Cliente cliente;
    /**
     * Fecha de hoy
     */
    private LocalDateTime fechaHoy;
    /**
     * Fecha registrada de la jugada
     */
    private String fechaJugada;
    /**
     * Apuesta realizada
     */
    private int apuesta;
    /**
     * Resultado de la jugada
     */
    private String resultado;
    /**
     * Mesa asociada a la jugada
     */
    private Mesa mesa;
    /**
     * La categoria en la que el cliente jugó en la jugada
     */
    private String categoriaEnJugada;

    /**
     * Es el constructor de la clase Jugada
     * @param cliente     es el cliente quien jugó la jugada
     * @param mesa        es la mesa que se jugó la jugada
     * @param fechaJugada es la fecha en la que se realizó la jugada
     * @param apuesta     es la apuesta que se apostó en la jugada
     * @param resultado   es el resultado de la jugada
     */
    public Jugada(Cliente cliente, Mesa mesa, String fechaJugada, int apuesta, String resultado) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.fechaJugada = fechaJugada;
        this.apuesta = apuesta;
        this.resultado = resultado;
        this.fechaHoy = LocalDateTime.now();
        this.categoriaEnJugada = cliente.getCategoriaSocio();
    }

    /**
     * Obtiene el cliente asociado
     * @return un objeto de tipo Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene la mesa asociada
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
     * Obtiene la apuesta de la jugada
     * @return un entero con la apuesta de la jugada
     */
    public int getApuesta() {
        return apuesta;
    }

    /**
     * Obtiene el resultado de la jugada
     *
     * @return un String con el resultado de la jugada
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Obtiene la fecha de hoy.
     * @return the fecha hoy
     */
    public LocalDateTime getFechaHoy() {
        return fechaHoy;
    }

    /**
     * Obtiene la categoria que se realizó la jugada.
     * @return la categoria que se realizó la jugada
     */
    public String getCategoriaEnJugada() {return categoriaEnJugada;}

}
