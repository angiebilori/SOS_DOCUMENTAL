package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Rol;

/**
 * @author Einer
 */
public class DAORol extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    //Metodos de Usuario--------------------------------------------------------
    public boolean registrarRol(Rol obj) {

        try {
            consulta = "insert into Rol (idRol, codigoRol, nomRol, descripcionRol, estadoRol) Values(?,?,?,?,?);";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdRol());
            pst.setString(2, obj.getCodigoRol());
            pst.setString(3, obj.getNomRol());
            pst.setString(4, obj.getDescripcionRol());
            pst.setString(5, obj.getEstadoRol());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registroRol: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error registroRol: " + e);
            }
        }
        return false;
    }

    public ArrayList<Rol> listarRol() {

        ArrayList<Rol> listar = new ArrayList();

        try {
            consulta = "Select * from rol;";

            pst = getConexion().prepareStatement(consulta);

            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    Rol obj = new Rol();

                    //Obtiene los datos de la base de datos
                    obj.setIdRol(rs.getString("idRol"));
                    obj.setCodigoRol(rs.getString("codigoRol"));
                    obj.setNomRol(rs.getString("nomRol"));
                    obj.setDescripcionRol(rs.getString("descripcionRol"));
                    obj.setEstadoRol(rs.getString("estadoRol"));
                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarRol: " + e);
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
                System.out.println("Error ListarRol: " + e);
            }
        }
        return null;
    }

    public boolean actualizarRol(Rol obj) {

        try {
            consulta = "update rol set nomRol=?, descripcionRol=?, estadoRol=? where idRol=?;";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getNomRol());
            pst.setString(2, obj.getDescripcionRol());
            pst.setString(3, obj.getEstadoRol());
            pst.setString(4, obj.getIdRol());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error actualizarRol: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error actualizarRol: " + e);
            }
        }
        return false;
    }

    public ArrayList<Rol> listarRolIdNom(Rol obj) {

        ArrayList<Rol> listar = new ArrayList();

        try {
            consulta = "Select * from rol where idRol like ? or nomRol like ?;";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdRol() + "%");
            pst.setString(2, obj.getNomRol() + "%");

            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                rs.beforeFirst();
                for (int i = 0; rs.next(); i++) {

                    obj = new Rol();
                    //Obtiene los datos de la base de datos
                    obj.setIdRol(rs.getString("idRol"));
                    obj.setCodigoRol(rs.getString("codigoRol"));
                    obj.setNomRol(rs.getString("nomRol"));
                    obj.setDescripcionRol(rs.getString("descripcionRol"));
                    obj.setEstadoRol(rs.getString("estadoRol"));
                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarRol: " + e);
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
                System.out.println("Error ListarRol: " + e);
            }
        }

        return null;
    }

    public ArrayList<Rol> listarRolId(Rol obj) {

        ArrayList<Rol> listar = new ArrayList();

        try {
            consulta = "Select * from rol where idRol = ?;";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdRol());

            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                rs.beforeFirst();

                while (rs.next()) {
                    obj = new Rol();

                    //Obtiene los datos de la base de datos
                    obj.setIdRol(rs.getString("idRol"));
                    obj.setCodigoRol(rs.getString("codigoRol"));
                    obj.setNomRol(rs.getString("nomRol"));
                    obj.setDescripcionRol(rs.getString("descripcionRol"));
                    obj.setEstadoRol(rs.getString("estadoRol"));
                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarRol: " + e);
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
                System.out.println("Error ListarRol: " + e);
            }
        }

        return null;
    }

    public String listarRolUltimoId() {

        try {
            consulta = "Select MAX(idRol) from rol;";
            pst = getConexion().prepareStatement(consulta);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                //rs.beforeFirst();

                String ultimoIdRol = rs.getString("MAX(idRol)");

                ultimoIdRol = Integer.toString(Integer.parseInt(ultimoIdRol) + 1);
                return ultimoIdRol;
            }
        } catch (SQLException e) {
            System.out.println("Error listarRolUltimoId: " + e);
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
                System.out.println("Error listarRolUltimoId: " + e);
            }
        }

        return null;
    }
}
