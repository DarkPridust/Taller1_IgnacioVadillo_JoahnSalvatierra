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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoJuego() {
        return tipoJuego;
    }

    public void setTipoJuego(String tipoJuego) {
        this.tipoJuego = tipoJuego;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getApuestaMin() {
        return apuestaMin;
    }

    public void setApuestaMin(int apuestaMin) {
        this.apuestaMin = apuestaMin;
    }

    public int getApuestaMax() {
        return apuestaMax;
    }

    public void setApuestaMax(int apuestaMax) {
        this.apuestaMax = apuestaMax;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
