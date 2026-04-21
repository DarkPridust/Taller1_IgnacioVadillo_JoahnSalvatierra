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
    private String estado;

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
                int apuestaMax, String estado) {

        this.id = id;
        this.tipoJuego = tipoJuego;
        this.descripcion = descripcion;
        this.apuestaMin = apuestaMin;
        this.apuestaMax = apuestaMax;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador de la mesa
     * @return un entero con el id
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la mesa
     * <p> Se genera de manera automaticamente y no se repite entre mesas</p>
     * @param id un entero con el identificador de la mesa
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el tiopo de juego de la mesa
     * @return un String con el tipo de juego de la mesa
     */
    public String getTipoJuego() {
        return tipoJuego;
    }

    /**
     * Establece el tipo de juego de la mesa
     * *<p> El tpo de juego puede cualquiera</p>
     * @param tipoJuego un String con el tipo de juego de la mesa
     */
    public void setTipoJuego(String tipoJuego) {
        this.tipoJuego = tipoJuego;
    }

    /**
     * Obtiene la descripcion de la mesa
     * @return un String con la descripción de la mesa
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion de la mesa
     * *<p> la descripcion puede contener cualquier cantidad de caracteres</p>
     * @param descripcion un String con la descripcion de la mesa
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la apuesta minima de la mesa
     * @return un entero con la apuesta minima
     */
    public int getApuestaMin() {
        return apuestaMin;
    }

    /**
     * Establece la apuesta minima de la mesa
     * *<p>No puede ser un valor negativo o igual a cero, ni mayor a la apuesta maxima </p>
     * @param apuestaMin un entero con la apuesta minima de la mesa
     */
    public void setApuestaMin(int apuestaMin) {
        this.apuestaMin = apuestaMin;
    }

    /**
     * Obtiene la apuesta maxima de la mesa
     * @return un entero con la apuesta maxima
     */
    public int getApuestaMax() {
        return apuestaMax;
    }

    /**
     * Establece la apuesta maxima
     * *<p>No puede ser menor a la apuesta inicial, ni tampoco caracteres</p>
     * @param apuestaMax un entero con la apuesta maxima de la mesa
     */
    public void setApuestaMax(int apuestaMax) {
        this.apuestaMax = apuestaMax;
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
     * * <p> los unicos estados posibles son disponible y cerrada </p>
     * @param estado un String con el estado de la mesa (disponible/cerrada)
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
