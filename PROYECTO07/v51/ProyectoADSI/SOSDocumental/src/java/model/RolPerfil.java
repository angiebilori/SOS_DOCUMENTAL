package model;

/**
 *
 * @author Einer
 */
public class RolPerfil extends Rol {

    Perfil idPerfil = new Perfil();

    public RolPerfil(String nomRol, String codigoRol, String descripcionRol, String estadoRol, String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        super(nomRol, codigoRol, descripcionRol, estadoRol, idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario);
    }

    public RolPerfil() {
    }

    public String getIdPerfil() {
        return idPerfil.getIdPerfil();
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil.setIdPerfil(idPerfil);
    }

}
