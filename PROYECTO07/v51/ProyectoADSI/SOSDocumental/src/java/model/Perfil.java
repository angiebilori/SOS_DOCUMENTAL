package model;

/**
 * @author Einer
 */
public class Perfil extends Rol {

    private String idPerfil;
    private String codigoPerfil;
    private String nomPerfil;
    private String descripcionPerfil;
    private String idModulo;
    private String estadoPerfil;
    
    Modulo mod = new Modulo();

    public Perfil(String idPerfil, String codigoPerfil, String nomPerfil, String descripcionPerfil, String idModulo, String estadoPerfil, String nomRol, String codigoRol, String descripcionRol, String estadoRol, String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        super(nomRol, codigoRol, descripcionRol, estadoRol, idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario);
        this.idPerfil = idPerfil;
        this.codigoPerfil = codigoPerfil;
        this.nomPerfil = nomPerfil;
        this.descripcionPerfil = descripcionPerfil;
        this.idModulo = idModulo;
        this.estadoPerfil = estadoPerfil;
    }

    public Perfil() {
    }

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(String codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public String getNomPerfil() {
        return nomPerfil;
    }

    public void setNomPerfil(String nomPerfil) {
        this.nomPerfil = nomPerfil;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public String getEstadoPerfil() {
        return estadoPerfil;
    }

    public void setEstadoPerfil(String estadoPerfil) {
        this.estadoPerfil = estadoPerfil;
    }
    
    public String getNomModulo() {
        return mod.getNomModulo();
    }

    public void setNomModulo(String nomModulo) {
        mod.setNomModulo(nomModulo);
    }

    
}
