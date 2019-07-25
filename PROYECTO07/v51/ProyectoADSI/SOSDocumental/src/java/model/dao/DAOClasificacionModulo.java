package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ClasificacionModulo;
import model.Conexion;

/**
 *
 * @author APRENDIZ
 */
public class DAOClasificacionModulo extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    //Metodos de Rol--------------------------------------------------------
    public ArrayList<ClasificacionModulo> listarClasificacionModulo() {

        ArrayList<ClasificacionModulo> listar = new ArrayList();

        try {
            consulta = "Select * from ClasificacionModulo";
            pst = getConexion().prepareStatement(consulta);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                while (rs.next()) {

                    ClasificacionModulo obj = new ClasificacionModulo();

                    //Obtiene los datos de la base de datos
                    obj.setIdClasificacionModulo(rs.getString("idClasificacionModulo"));
                    obj.setNomClasificacionModulo(rs.getString("nomClasificacionModulo"));
                    obj.setDescripcionClasificacionModulo(rs.getString("descripcionClasificacionModulo"));
                    obj.setEstadoClasificacionModulo(rs.getString("estadoClasificacionModulo"));

                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error listarClasificacionModulo: " + e);
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
                System.out.println("Error listarClasificacionModulo: " + e);
            }
        }
        return null;
    }
}
