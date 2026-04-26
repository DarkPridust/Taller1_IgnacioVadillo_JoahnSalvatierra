/**
 *Representa a la entidad Mesa
 */
public class Mesa {
    /**
     *Corresponde al identificador de Mesa
     */
    private int id;
    /**
     *Tipo de juego de la Mesa
     */
    private String tipoJuego;
    /**
     *Descripción de la Mesa
     */
    private String descripcion;
    /**
     *Apuesta minima de la apuesta
     */
    private int apuestaMin;
    /**
     *Apuesta maxima de la apuesta
     */
    private int apuestaMax;
    /**
     *Estado de la mesa (cerrada/disponible)
     */
    private String estado;

    /**
     * El constructor de la clase Mesa
     * @param id es la ID de la Mesa
     * @param tipoJuego es la ID de la Mesa
     * @param descripcion es la descripción de la Mesa
     * @param apuestaMin es la apuesta mínima que se puede ingresar en la Mesa
     * @param apuestaMax es la apuesta máxima que se puede ingresar en la Mesa
     * @param estado es el estado en el que se encuentra la Mesa
     */
    public Mesa(int id, String tipoJuego, String descripcion, int apuestaMin,int apuestaMax, String estado) {
        this.id = id;
        this.tipoJuego = tipoJuego;
        this.descripcion = descripcion;
        this.apuestaMin = apuestaMin;
        this.apuestaMax = apuestaMax;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador de la mesa
     * @return un entero con su id
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el tipo de juego de la mesa
     * @return un String con el tipo de juego de la mesa
     */
    public String getTipoJuego() {
        return tipoJuego;
    }

    /**
     * Obtiene la descripción de la mesa
     * @return un String con la descripción de la mesa
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene la apuesta minima de la mesa
     * @return un entero con la apuesta minima
     */
    public int getApuestaMin() {
        return apuestaMin;
    }

    /**
     * Obtiene la apuesta maxima de la mesa
     * @return un entero con la apuesta maxima
     */
    public int getApuestaMax() {
        return apuestaMax;
    }

    /**
     * Obtiene el estado de la mesa
     * @return un string con el estado de la mesa
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la mesa
     * * <p> Los unicos estados posibles son disponible y cerrada </p>
     * @param estado un String con el estado de la mesa (disponible/cerrada)
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
