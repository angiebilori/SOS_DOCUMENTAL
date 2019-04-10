package model;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Einer
 */
public class manual extends conexion {

    private String error;
    private InputStream documento;

    public manual() {
    }

    public manual(String error, InputStream documento) {
        this.error = error;
        this.documento = documento;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public InputStream getDocumento() {
        return documento;
    }

    public void setDocumento(InputStream documento) {
        this.documento = documento;
    }

    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public boolean agregarManual() {
        try {
            String consulta = "insert into manual(idManual, manual) values(?,?);";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, 0);
            pst.setBlob(2, documento);

            if (pst.executeUpdate() == 1) { //Modificaciones
                error = "El documento se registro";
                return true;
            }
        } catch (SQLException e) {
            error = "Error: " + e;
        }
        return false;
    }

    public boolean ActualizarManual() {
        try {
            String Consulta = "Update manual set manual = ? where idManual =?; ";
            pst = getConexion().prepareStatement(Consulta);
            pst.setBlob(1, documento);
            pst.setInt(2, 1);
            if (pst.executeUpdate() == 1) {
                error = "EL documento se actualizo correctamente";
                return true;
            }
        } catch (SQLException e) {
            error = "Error: " + e;
        }
        return false;
    }

    public boolean ConsultManual() {
        try {
            String Consulta = "select * from manual;";
            pst = getConexion().prepareStatement(Consulta);

            this.rs = pst.executeQuery();//Solo para consultas

            if (rs.absolute(1)) {
                this.documento = rs.getBinaryStream("manual");
                return true;
            }
        } catch (SQLException e) {
            error = "Error: " + e;
        }
        return false;
    }
}
