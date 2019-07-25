package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Fecha;
import model.ReporteEspecifico;

/**
 * @author Einer
 */
public class DAOReporteEspecifico extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    Fecha fec = new Fecha();

    //Metodos de Reporte Especifico--------------------------------------------------------
    public boolean registroRE(ReporteEspecifico obj) {
        try {
            consulta = "insert into reporteEspecifico (idReporteEspecifico, idDocumento, versionDocumento, fechaDocumento, idUsuario) Values(?,?,?,?,?);";

            pst = getConexion().prepareStatement(consulta);

            pst.setInt(1, 0);
            pst.setString(2, obj.getIdDocumento());
            pst.setString(3, obj.getVersionDocumento());
            pst.setString(4, obj.getFechaRegistroDocumento());
            pst.setString(5, obj.getIdUsuario());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registroReporteEspecifico: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error registroReporteEspecifico: " + e);
            }
        }
        return false;
    }

    public ArrayList<ReporteEspecifico> listarREFechaIdDocumento(String idDocumento, String fechaIni, String fechaFin) {

        ArrayList<ReporteEspecifico> listar = new ArrayList();
        try {
            consulta = "Select * from reporteEspecifico where idDocumento like ? and (fechaDocumento >= ? and fechaDocumento <= ?) order by idDocumento asc;";

            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, "%" + idDocumento + "%");
            pst.setString(2, fechaIni + "%");
            pst.setString(3, fechaFin + "%");
            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                //datos.clear();//Limpiar el arraylist 
                rs.beforeFirst();//Sirve para no perder el primer registro

                while (rs.next()) {
                    ReporteEspecifico obj = new ReporteEspecifico();

                    obj.setIdReporteEspecifico(rs.getString("idReporteEspecifico"));
                    obj.setIdDocumento(rs.getString("idDocumento"));
                    obj.setFechaRegistroDocumento(fec.sumaHoras(rs.getString("fechaDocumento")));
                    obj.setVersionDocumento(rs.getString("versionDocumento"));
                    obj.setIdUsuario(rs.getString("idUsuario"));

                    listar.add(obj);
                }
                return listar;
            }

        } catch (SQLException e) {
            System.out.println("Error consultaReporteEspecifico: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Error consultaReporteEspecifico: " + e);
            }
        }
        return null;
    }

    public boolean actualizarRE() {
        return false;
    }

    public boolean eliminarRE() {
        return false;
    }

}
