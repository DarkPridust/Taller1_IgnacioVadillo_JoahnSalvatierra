public interface SistemaCasino {
    public boolean iniciarSesion(String nombreUsuario, String contrasenia);
    public String registrarSesionJuego(String fecha, double resultado);
    public String comprobanteSesionJuego();
    public String consultarHistorial(String fecha);
}
