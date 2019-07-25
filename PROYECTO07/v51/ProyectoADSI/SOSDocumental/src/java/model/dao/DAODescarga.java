package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Actualizacion;
import model.Conexion;
import model.Descarga;
import model.Fecha;

/**
 * @author Einer
 */
public class DAODescarga extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;
    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    Fecha fec = new Fecha();//Clase Fecha donde se utilizará el metodo sumar 5 horas

    //Metodos de Descarga--------------------------------------------------------
    public boolean registrarDescarga(Descarga obj) {
        try {
            consulta = "insert into descarga(idDescarga, fechaDescarga, idDocumento, idUsuario) values(?,?,?,?)";

            pst = getConexion().prepareStatement(consulta);

            pst.setInt(1, 0);
            pst.setString(2, obj.getFechaDescarga());
            pst.setString(3, obj.getIdDocumento());
            pst.setString(4, obj.getIdUsuario());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registrarDescarga: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    //conn.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error registrarDescarga: " + e);
            }
        }
        return false;
    }

    public ArrayList<Descarga> listarDescargaFechaIdDocumento(String idDocumento, String fechaIni, String fechaFin) {

        ArrayList<Descarga> listar = new ArrayList();
        try {
            consulta = "Select * from Descarga where idDocumento like ? and (fechaDescarga >= ? and fechaDescarga <= ?) order by idDocumento asc;";

            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, "%" + idDocumento + "%");
            pst.setString(2, fechaIni + "%");
            pst.setString(3, fechaFin + "%");
            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                //datos.clear();//Limpiar el arraylist 
                rs.beforeFirst();//Sirve para no perder el primer registro

                while (rs.next()) {
                    Descarga obj = new Descarga();

                    obj.setIdDescarga(rs.getString("idDescarga"));
                    obj.setIdDocumento(rs.getString("idDocumento"));
                    obj.setFechaRegistroDocumento(fec.sumaHoras(rs.getString("fechaDescarga")));
                    obj.setIdUsuario(rs.getString("idUsuario"));

                    listar.add(obj);
                }
                return listar;
            }

        } catch (SQLException e) {
            System.out.println("Error consultaReporteDescarga: " + e);
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
                System.out.println("Error consultaReporteDescarga: " + e);
            }
        }
        return null;
    }

}
