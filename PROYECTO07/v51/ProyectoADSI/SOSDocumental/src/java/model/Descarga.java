package model;

import java.io.InputStream;

/**
 * @author Einer
 */
public class Descarga extends Documento {

    private String idDescarga;
    private String fechaDescarga;
    Usuario usu = new Usuario();

    public Descarga(String idDescarga, String fechaDescarga, String idDocumento, String nomDocumento, String fechaRegistroDocumento, InputStream documento, String versionDocumento, String tipoDocumento, String estadoDocumento, String descripcionDocumento, String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        super(idDocumento, nomDocumento, fechaRegistroDocumento, documento, versionDocumento, tipoDocumento, estadoDocumento, descripcionDocumento, idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario);
        this.idDescarga = idDescarga;
        this.fechaDescarga = fechaDescarga;
    }

    public Descarga() {
    }

    public String getIdDescarga() {
        return idDescarga;
    }

    public void setIdDescarga(String idDescarga) {
        this.idDescarga = idDescarga;
    }
    
    public String getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(String fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }
}
