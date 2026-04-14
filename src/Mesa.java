/**
 *Representa a la entidad Mesa
 */
public class Mesa {
    /**
     *corresponde al iidentificador de Mesa
     */
    private int id;
    /**
     *tipo de juego de la Mesa
     */
    private String tipoJuego;
    /**
     *Descripcion de la Mesa
     */
    private String descripcion;
    /**
     *Apuessta minima de la apuesta
     */
    private int apuestaMin;
    /**
     *Apuessta maxima de la apuesta
     */
    private int apuestaMax;
    /**
     *estado de la mesa (cerrada/disponible)
     */
    private boolean estado;

    /**
     * El contructor de la clase Cliente
     * @param id
     * @param tipoJuego
     * @param descripcion
     * @param apuestaMin
     * @param apuestaMax
     * @param estado
     */
    public Mesa(int id, String tipoJuego, String descripcion, int apuestaMin,
                int apuestaMax, boolean estado) {

        this.id = id;
        this.tipoJuego = tipoJuego;
        this.descripcion = descripcion;
        this.apuestaMin = apuestaMin;
        this.apuestaMax = apuestaMax;
        this.estado = estado;
    }

    /**
     * Obtiene
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Establece
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene
     * @return
     */
    public String getTipoJuego() {
        return tipoJuego;
    }

    /**
     * Establece
     * @param tipoJuego
     */
    public void setTipoJuego(String tipoJuego) {
        this.tipoJuego = tipoJuego;
    }

    /**
     * Obtiene
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene
     * @return
     */
    public int getApuestaMin() {
        return apuestaMin;
    }

    /**
     * Establece
     * @param apuestaMin
     */
    public void setApuestaMin(int apuestaMin) {
        this.apuestaMin = apuestaMin;
    }

    /**
     * Obtiene
     * @return
     */
    public int getApuestaMax() {
        return apuestaMax;
    }

    /**
     * Establece
     * @param apuestaMax
     */
    public void setApuestaMax(int apuestaMax) {
        this.apuestaMax = apuestaMax;
    }

    /**
     * Obtiene
     * @return
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece
     * @param estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
