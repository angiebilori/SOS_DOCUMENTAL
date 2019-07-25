package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Manual;

/**
 *
 * @author Einer
 */
public class DAOManual extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    public boolean agregarManual(Manual obj) {
        try {
            consulta = "insert into manual(idManual, manual) values(?,?);";
            pst = getConexion().prepareStatement(consulta);

            pst.setInt(1, 0);
            pst.setBlob(2, obj.getDocumento());

            if (pst.executeUpdate() == 1) { //Modificaciones
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error agregarManual: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error agregarManual: " + e);
            }
        }
        return false;
    }

    public boolean actualizarManual(Manual obj) {
        try {
            consulta = "Update manual set manual = ? where idManual =?; ";
            pst = getConexion().prepareStatement(consulta);
            pst.setBlob(1, obj.getDocumento());
            pst.setInt(2, 1);
            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error ActualizarManual: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error ActualizarManual: " + e);
            }
        }
        return false;
    }

    public ArrayList<Manual> listarManual(Manual obj) {
            ArrayList<Manual> listar = new ArrayList();

        try {
            String Consulta = "select * from manual;";
            pst = getConexion().prepareStatement(Consulta);

            this.rs = pst.executeQuery();//Solo para consultas


            if (rs.absolute(1)) {
                obj = new Manual();
                
                obj.setDocumento(rs.getBinaryStream("manual"));
                
                listar.add(obj);
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarManual: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error ListarManual: " + e);
            }
        }
        return null;
    }
}
