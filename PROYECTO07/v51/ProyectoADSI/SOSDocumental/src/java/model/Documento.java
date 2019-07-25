package model;

import java.io.InputStream;

/**
 * @author Einer
 */
public class Documento extends Usuario{

    private String idDocumento;
    private String nomDocumento;
    private String fechaRegistroDocumento;
     private InputStream documento;
    private String versionDocumento;
    private String tipoDocumento;
    private String estadoDocumento;
    private String descripcionDocumento;

    public Documento(String idDocumento, String nomDocumento, String fechaRegistroDocumento, InputStream documento, String versionDocumento, String tipoDocumento, String estadoDocumento, String descripcionDocumento, String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        super(idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario);
        this.idDocumento = idDocumento;
        this.nomDocumento = nomDocumento;
        this.fechaRegistroDocumento = fechaRegistroDocumento;
        this.documento = documento;
        this.versionDocumento = versionDocumento;
        this.tipoDocumento = tipoDocumento;
        this.estadoDocumento = estadoDocumento;
        this.descripcionDocumento = descripcionDocumento;
    }    

    public Documento() {
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNomDocumento() {
        return nomDocumento;
    }

    public void setNomDocumento(String nomDocumento) {
        this.nomDocumento = nomDocumento;
    }

    public String getFechaRegistroDocumento() {
        return fechaRegistroDocumento;
    }

    public void setFechaRegistroDocumento(String fechaRegistroDocumento) {
        this.fechaRegistroDocumento = fechaRegistroDocumento;
    }

    public InputStream getDocumento() {
        return documento;
    }

    public void setDocumento(InputStream documento) {
        this.documento = documento;
    }

    public String getVersionDocumento() {
        return versionDocumento;
    }

    public void setVersionDocumento(String versionDocumento) {
        this.versionDocumento = versionDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(String estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }

    public String getDescripcionDocumento() {
        return descripcionDocumento;
    }

    public void setDescripcionDocumento(String descripcionDocumento) {
        this.descripcionDocumento = descripcionDocumento;
    }
    
}
