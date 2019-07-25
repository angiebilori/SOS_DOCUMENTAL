package model;

import java.io.InputStream;

/**
 * @author Einer
 */
public class Manual extends Conexion {

    private InputStream documento;

    public Manual() {
    }

    public Manual(InputStream documento) {
        this.documento = documento;
    }

    public InputStream getDocumento() {
        return documento;
    }

    public void setDocumento(InputStream documento) {
        this.documento = documento;
    }
}
