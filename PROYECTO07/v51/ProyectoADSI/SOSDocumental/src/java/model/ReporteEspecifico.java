package model;

import java.io.InputStream;

/**
 * @author Einer
 */
public class ReporteEspecifico extends Documento {

    Usuario usu = new Usuario();
    private String idReporteEspecifico;

    public ReporteEspecifico(String idReporteEspecifico, String idDocumento, String nomDocumento, String fechaRegistroDocumento, InputStream documento, String versionDocumento, String tipoDocumento, String estadoDocumento, String descripcionDocumento, String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        super(idDocumento, nomDocumento, fechaRegistroDocumento, documento, versionDocumento, tipoDocumento, estadoDocumento, descripcionDocumento, idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario);
        this.idReporteEspecifico = idReporteEspecifico;
    }

    public ReporteEspecifico() {
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public String getIdReporteEspecifico() {
        return idReporteEspecifico;
    }

    public void setIdReporteEspecifico(String idReporteEspecifico) {
        this.idReporteEspecifico = idReporteEspecifico;
    }
}
