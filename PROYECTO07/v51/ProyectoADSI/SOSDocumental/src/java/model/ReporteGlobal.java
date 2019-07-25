package model;

import java.io.InputStream;

/**
 * @author Einer
 */
public class ReporteGlobal extends Documento {

    private String idReporteGlobal;
    private String cantidadDescargas;
    private String cantidadBusquedas;
    private String fechaReporteGlobal;

    public ReporteGlobal(String idReporteGlobal, String cantidadDescargas, String cantidadBusquedas, String fechaReporteGlobal, String idDocumento, String nomDocumento, String fechaRegistroDocumento, InputStream documento, String versionDocumento, String tipoDocumento, String estadoDocumento, String descripcionDocumento, String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        super(idDocumento, nomDocumento, fechaRegistroDocumento, documento, versionDocumento, tipoDocumento, estadoDocumento, descripcionDocumento, idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario);
        this.idReporteGlobal = idReporteGlobal;
        this.cantidadDescargas = cantidadDescargas;
        this.cantidadBusquedas = cantidadBusquedas;
        this.fechaReporteGlobal = fechaReporteGlobal;
    }    

    public ReporteGlobal() {
    }
    
    
    public String getIdReporteGlobal() {
        return idReporteGlobal;
    }

    public void setIdReporteGlobal(String idReporteGlobal) {
        this.idReporteGlobal = idReporteGlobal;
    }   

    public String getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(String cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public String getCantidadBusquedas() {
        return cantidadBusquedas;
    }

    public void setCantidadBusquedas(String cantidadBusquedas) {
        this.cantidadBusquedas = cantidadBusquedas;
    }

    public String getFechaReporteGlobal() {
        return fechaReporteGlobal;
    }

    public void setFechaReporteGlobal(String fechaReporteGlobal) {
        this.fechaReporteGlobal = fechaReporteGlobal;
    }

}