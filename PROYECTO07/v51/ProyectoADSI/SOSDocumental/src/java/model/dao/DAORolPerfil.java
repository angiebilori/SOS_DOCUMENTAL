package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Conexion;
import model.RolPerfil;

/**
 *
 * @author Einer
 */
public class DAORolPerfil extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    public boolean agregarRolPerfil(RolPerfil obj) {
        try {
            consulta = "insert into RolPerfil (idRolPerfil, idRol, idPerfil) Values(?,?,?);";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, "0");
            pst.setString(2, obj.getIdRol());
            pst.setString(3, obj.getIdPerfil());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error agregarRolPerfil: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error agregarRolPerfil: " + e);
            }
        }

        return false;
    }

    public boolean eliminarRolPerfil(RolPerfil obj) {
        try {
            consulta = "DELETE FROM RolPerfil where idRol = ?;";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdRol());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error eliminarRolPerfil: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error eliminarRolPerfil: " + e);
            }
        }
        return false;
    }
}
