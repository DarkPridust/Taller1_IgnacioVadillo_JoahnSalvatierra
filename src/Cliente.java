/**
 *Representa a la entidad Cliente
 */
public class Cliente {
    /**
     * rut del cliente
     */
    private String rut;
    /**
     * nombre del cliente
     */
    private String nombre;
    /**
     * apellido paterno del cliente
     */
    private String apellidoPaterno;
    /**
     * apellido materno del cliente
     */
    private String apellidoMaterno;
    /**
     * nombre de usuario del cliente
     */
    private String nombreUsuario;
    /**
     * contrasenia del cliente
     */
    private String contrasenia;
    /**
     * Categoria de socio del cliente
     */
    private String categoriaSocio;

    /**
     * Constructor de la clase Cliente
     * @param rut
     * @param nombre
     * @param apellidoPaterno
     * @param appelidoMaterno
     * @param nombreUsuario
     * @param contrasenia
     * @param categoriaSocio
     */
    public Cliente(String rut, String nombre, String apellidoPaterno, String appelidoMaterno,
                   String nombreUsuario, String contrasenia, String categoriaSocio) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = appelidoMaterno;
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
     * Establece el rut del cliente
     * <p>El rut debe de ser unico por cliente (no se puede repetir)</p>
     * @param rut un String con el rut del cliente
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Obtiene el nombre del cliente
     * @return un string con el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente
     * <p>Se admite cualquier extension de nombre</p>
     * @param nombre un String con el nombre del cliente
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene el apellido paterno del cliente
     * @return un string con el apellido paterno del cliente
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    /**
     * Establece el apellido paterno del cliente
     * *<p>Se admite cualquier extension de apellido</p>
     * @param apellidoPaterno un String con el apellido paterno del cliente
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    /**
     * Obtiene el apellido materno del cliente
     * @return un string con el apellido materno del cliente
     */
    public String getAppelidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del cliente
     * *<p>Se admite cualquier extension de apellido</p>
     * @param apellidoMaterno un String con el apellido materno del cliente
     */
    public void setAppelidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    /**
     * Obtiene el nombre de usuario del cliente
     * @return un string con el nombre de usuario del cliente
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // El limite de caracteres es para que un cliente no pueda tener un nombre con una cantidad de caracteres absurda.
    /**
     * Establece el nombre de usuario de cliente
     * *<p>Se admite un nombre de usuario con un limite de 12 caracteres </p>
     * @param nombreUsuario un String con el nombre del usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
     * * <p> La contraseña inicial debe ser el rut sin puntos ni guion. Para cambiar la contraseña esta debe contener: almenos 8 caracteres, almenos una letra mayuscula,
     * contener al menos un digito numerico. Ademas la nueva contraseña debe de ser distinta a la anterior.</p>
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
     * * <p> los cliente empiezan con la categoria normal. Los valores permitidos son: Normal, Plata, Oro o Platino.
     * Cualquier otro valor puede causar comportamientos inesperados en el calculo de beneficios.</p>
     * @param categoriaSocio un String con la categoria del cliente ("Vacio (Cliente normal)", "Plata","Oro" o "Platino").
     */
    public void setCategoriaSocio(String categoriaSocio) {
        this.categoriaSocio = categoriaSocio;
    }

    public String getNombreCompleto() {return nombre +" "+apellidoPaterno +" "+apellidoMaterno;}
}
