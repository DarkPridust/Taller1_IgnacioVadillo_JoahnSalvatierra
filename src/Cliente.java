/**
 *Representa a la entidad Cliente
 */
public class Cliente {
    /**
     * Rut del cliente
     */
    private String rut;
    /**
     * Nombre del cliente
     */
    private String nombre;
    /**
     * Apellido paterno del cliente
     */
    private String apellidoPaterno;
    /**
     * Apellido materno del cliente
     */
    private String apellidoMaterno;
    /**
     * Nombre de usuario del cliente
     */
    private String nombreUsuario;
    /**
     * Contraseña del cliente
     */
    private String contrasenia;
    /**
     * Categoria de socio del cliente
     */
    private String categoriaSocio;

    /**
     * Constructor de la clase Cliente
     * @param rut es el rut del cliente
     * @param nombre es el nombre del cliente
     * @param apellidoPaterno es el apellido paterno del cliente
     * @param apellidoMaterno es el apellido materno del cliente
     * @param nombreUsuario es el nombre de usuario del cliente
     * @param contrasenia es la contraseña del cliente
     * @param categoriaSocio es la categoría social en la que se encuentra el cliente
     */
    public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno,
                   String nombreUsuario, String contrasenia, String categoriaSocio) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.categoriaSocio = categoriaSocio;
    }

    /**
     * Obtiene el rut del cliente
     * @return un string con el rut del cliente
     */
    public String getRut() {
        return rut;
    }

    /**
     * Obtiene el nombre del cliente
     * @return un string con el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el apellido paterno del cliente
     * @return un string con el apellido paterno del cliente
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del cliente
     * @return un string con el apellido materno del cliente
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Obtiene el nombre de usuario del cliente
     * @return un string con el nombre de usuario del cliente
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Obtiene la contraseña del cliente
     * @return un string con la contraseña del cliente
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del cliente.
     * * <p> La contraseña inicial debe ser el rut sin puntos ni guion. Para cambiar la contraseña esta debe contener: al menos 8 caracteres, al menos una letra mayúscula,
     * contener al menos un dígito numerico. Además, la nueva contraseña debe de ser distinta a la anterior.</p>
     * @param contrasenia un String con la contraseña del cliente
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene la categoria del cliente
     * @return un string con la categoria del cliente
     */
    public String getCategoriaSocio() {
        return categoriaSocio;
    }

    /**
     * Establece la categoria del cliente.
     * * <p> Los clientes empiezan con la categoria normal. Los valores permitidos son: Normal, Plata, Oro o Platino.
     * Cualquier otro valor puede causar comportamientos inesperados en el cálculo de beneficios.</p>
     * @param categoriaSocio un String con la categoria del cliente ("Vacío (Cliente normal)", "Plata", "Oro" o "Platino").
     */
    public void setCategoriaSocio(String categoriaSocio) {
        this.categoriaSocio = categoriaSocio;
    }

    /**
     * Obtiene el nombre completo del usuario.
     * @return
     */
    public String getNombreCompleto() {return nombre +" "+apellidoPaterno +" "+apellidoMaterno;}
}
