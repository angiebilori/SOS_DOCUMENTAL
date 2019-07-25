/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;

/**
 *
 * @author Einer
 */
public class Retencion extends Documento {

    private String idRetencion;
    private String fechaVencimiento;

    public Retencion(String idRetencion, String fechaVencimiento, String idDocumento, String nomDocumento, String fechaRegistroDocumento, InputStream documento, String versionDocumento, String tipoDocumento, String estadoDocumento, String descripcionDocumento, String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        super(idDocumento, nomDocumento, fechaRegistroDocumento, documento, versionDocumento, tipoDocumento, estadoDocumento, descripcionDocumento, idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario);
        this.idRetencion = idRetencion;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getIdRetencion() {
        return idRetencion;
    }

    public void setIdRetencion(String idRetencion) {
        this.idRetencion = idRetencion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

}
