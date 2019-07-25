package model;

/**
 * @author Einer
 */
public class Rol extends Usuario {

    private String nomRol;
    private String codigoRol;
    private String descripcionRol;
    private String estadoRol;

    public Rol(String nomRol, String codigoRol, String descripcionRol, String estadoRol, String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        super(idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario);
        this.nomRol = nomRol;
        this.codigoRol = codigoRol;
        this.descripcionRol = descripcionRol;
        this.estadoRol = estadoRol;
    }

    public Rol() {
    }

    public String getNomRol() {
        return nomRol;
    }

    public void setNomRol(String nomRol) {
        this.nomRol = nomRol;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public String getEstadoRol() {
        return estadoRol;
    }

    public void setEstadoRol(String estadoRol) {
        this.estadoRol = estadoRol;
    }

}
