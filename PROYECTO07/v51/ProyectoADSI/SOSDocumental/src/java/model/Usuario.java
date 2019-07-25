package model;
import org.apache.commons.codec.digest.DigestUtils;

/*
 * @author Einer
 */
public class Usuario {

    //Definicion de variables privadas - atributos
    private String idUsuario;
    private String primNombre;
    private String segNombre;
    private String primApellido;
    private String segApellido;
    private String correo;
    private String idRol;
    private String contrasena;
    private String estadoUsuario;

    //Constructores - encapsulacion de datos

    public Usuario(String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        this.idUsuario = idUsuario;
        this.primNombre = primNombre;
        this.segNombre = segNombre;
        this.primApellido = primApellido;
        this.segApellido = segApellido;
        this.correo = correo;
        this.idRol = idRol;
        this.contrasena = contrasena;
        this.estadoUsuario = estadoUsuario;
    }

    public Usuario() {
    }

    //Getters y setters obtener y asignar informacion a las variables
        public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPrimNombre() {
        return primNombre;
    }

    public void setPrimNombre(String primNombre) {
        this.primNombre = primNombre;
    }

    public String getSegNombre() {
        return segNombre;
    }

    public void setSegNombre(String segNombre) {
        this.segNombre = segNombre;
    }

    public String getPrimApellido() {
        return primApellido;
    }

    public void setPrimApellido(String primApellido) {
        this.primApellido = primApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public void setSegApellido(String segApellido) {
        this.segApellido = segApellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String rol) {
        this.idRol = rol;
    }

    public String getContrasena() {
        contrasena = DigestUtils.md5Hex(contrasena);
       //System.out.println("Texto Encriptado Falta inplementarlo clase usuario getContrasena con MD5 : " + contrasena);
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }
}
