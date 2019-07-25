package model;

import java.io.InputStream;

/**
 *
 * @author Einer
 */
public class Actualizacion extends Documento {

    public Actualizacion(String idDocumento, String nomDocumento, String fechaRegistroDocumento, InputStream documento, String versionDocumento, String tipoDocumento, String estadoDocumento, String descripcionDocumento, String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String correo, String idRol, String contrasena, String estadoUsuario) {
        super(idDocumento, nomDocumento, fechaRegistroDocumento, documento, versionDocumento, tipoDocumento, estadoDocumento, descripcionDocumento, idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario);
    }

    public Actualizacion() {
    }
    
}
