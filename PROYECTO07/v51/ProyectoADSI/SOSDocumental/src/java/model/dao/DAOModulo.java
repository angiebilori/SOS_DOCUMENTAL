package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Modulo;

/**
 *
 * @author Einer
 */
public class DAOModulo extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    //Metodos de Rol--------------------------------------------------------
    public ArrayList<Modulo> listarModulo() {

        ArrayList<Modulo> listar = new ArrayList();

        try {
            consulta = "Select * from Modulo";

            pst = getConexion().prepareStatement(consulta);

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                 while(rs.next()) {

                    Modulo obj = new Modulo();
                    //Obtiene los datos de la base de datos
                    obj.setIdModulo(rs.getString("idModulo"));
                    obj.setCodigoModulo(rs.getString("codigoModulo"));
                    obj.setNomModulo(rs.getString("nomModulo"));
                    obj.setDescripcionModulo(rs.getString("descripcionModulo"));
                    obj.setLinkAccesoModulo(rs.getString("linkAccesoModulo"));
                    obj.setEstadoModulo(rs.getString("estadoModulo"));
                    obj.setIdClasificacionModulo(rs.getString("idClasificacionModulo"));
                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarModulo: " + e);
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
                System.out.println("Error ListarModulo: " + e);
            }
        }

        return null;
    }

    public ArrayList<Modulo> listarModuloId(Modulo obj) {

        ArrayList<Modulo> listar = new ArrayList();

        try {
            consulta = "Select * from Modulo where idModulo = ?;";

            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, obj.getIdModulo());
            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                while(rs.next()) {

                    obj = new Modulo();
                    //Obtiene los datos de la base de datos
                    obj.setIdModulo(rs.getString("idModulo"));
                    obj.setCodigoModulo(rs.getString("codigoModulo"));
                    obj.setNomModulo(rs.getString("nomModulo"));
                    obj.setDescripcionModulo(rs.getString("descripcionModulo"));
                    obj.setLinkAccesoModulo(rs.getString("linkAccesoModulo"));
                    obj.setEstadoModulo(rs.getString("estadoModulo"));
                    obj.setIdClasificacionModulo(rs.getString("idClasificacionModulo"));
                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarModulo: " + e);
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
                System.out.println("Error ListarModulo: " + e);
            }
        }

        return null;
    }

    public ArrayList<ArrayList> listarModuloIdClasificacion(Modulo obj) {

        ArrayList<ArrayList> listar = new ArrayList();

        try {

            consulta = "Select * from Modulo where idClasificacionModulo = ?;";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdClasificacionModulo());

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                for (int i = listar.size(); rs.next(); i++) {

                    listar.add(new ArrayList());
                    //Obtiene los datos de la base de datos
                    listar.get(i).add(rs.getString("idModulo"));
                    listar.get(i).add(rs.getString("codigoModulo"));
                    listar.get(i).add(rs.getString("nomModulo"));
                    listar.get(i).add(rs.getString("descripcionModulo"));
                    listar.get(i).add(rs.getString("linkAccesoModulo"));
                    listar.get(i).add(rs.getString("estadoModulo"));
                    listar.get(i).add(rs.getString("idClasificacionModulo"));
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarModulo: " + e);
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
                System.out.println("Error ListarModulo: " + e);
            }
        }

        return null;
    }

}
