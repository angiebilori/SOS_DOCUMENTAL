package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Perfil;

/**
 * @author Einer
 */
public class DAOPerfil extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    //Metodos de Rol--------------------------------------------------------
    public boolean registrarPerfil(Perfil obj) {

        try {
            consulta = "insert into Perfil (idPerfil, codigoPerfil, nomPerfil, descripcionPerfil, idModulo, estadoPerfil) Values(?,?,?,?,?,?);";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, "0");
            pst.setString(2, obj.getCodigoPerfil());
            pst.setString(3, obj.getNomPerfil());
            pst.setString(4, obj.getDescripcionPerfil());
            pst.setString(5, obj.getIdModulo());
            pst.setString(6, obj.getEstadoPerfil());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registrarPerfil: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error registrarPerfil: " + e);
            }
        }
        return false;
    }

    public ArrayList<Perfil> listarPerfilIdRol(Perfil obj) {

        ArrayList<Perfil> listar = new ArrayList();

        try {
            consulta = "Select * from perfil p inner join rolPerfil rp on rp.idPerfil = p.idPerfil where rp.idRol=?;";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdRol());

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                while (rs.next()) {

                    obj = new Perfil();

                    //Obtiene los datos de la base de datos
                    obj.setIdPerfil(rs.getString("idPerfil"));
                    obj.setCodigoPerfil(rs.getString("codigoPerfil"));
                    obj.setNomPerfil(rs.getString("nomPerfil"));
                    obj.setDescripcionPerfil(rs.getString("descripcionPerfil"));
                    obj.setIdModulo(rs.getString("idModulo"));
                    obj.setEstadoPerfil(rs.getString("estadoPerfil"));
                    listar.add(obj);
                }

                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error listarPerfilIdRol: " + e);
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
                System.out.println("Error listarPerfilIdRol: " + e);
            }
        }

        return null;
    }

    public boolean actualizarPerfilCod(Perfil obj) {

        try {
            consulta = "update Perfil set nomPerfil=?, descripcionPerfil = ?, idModulo=?, estadoPerfil=? where codigoPerfil=?;";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getNomPerfil());
            pst.setString(2, obj.getDescripcionPerfil());
            pst.setString(3, obj.getIdModulo());
            pst.setString(4, obj.getEstadoPerfil());
            pst.setString(5, obj.getCodigoPerfil());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error actualizarPerfilCod: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error actualizarPerfilCod: " + e);
            }
        }
        return false;
    }

    public boolean actualizarPerfilId(Perfil obj) {

        try {
            consulta = "update Perfil set codigoPerfil = ?, nomPerfil = ?, descripcionPerfil = ?, idModulo = ?, estadoPerfil = ? where idPerfil = ?;";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getCodigoPerfil());
            pst.setString(2, obj.getNomPerfil());
            pst.setString(3, obj.getDescripcionPerfil());
            pst.setString(4, obj.getIdModulo());
            pst.setString(5, obj.getEstadoPerfil());
            pst.setString(6, obj.getIdPerfil());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error actualizarPerfilId: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error actualizarPerfilId: " + e);
            }
        }
        return false;
    }

    public ArrayList<Perfil> listarPerfilIdMod(Perfil obj) {

        ArrayList<Perfil> listar = new ArrayList();

        try {
            consulta = "Select * from perfil p inner join modulo m on p.idModulo = m.idModulo where m.idModulo = ? order by p.codigoPerfil ASC;";

            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, obj.getIdModulo());
            rs = pst.executeQuery();
            if (rs.absolute(1)) {

                rs.beforeFirst();

                while (rs.next()) {
                    obj = new Perfil();
                    //Obtiene los datos de la base de datos
                    obj.setIdPerfil(rs.getString("idPerfil"));
                    obj.setCodigoPerfil(rs.getString("codigoPerfil"));
                    obj.setNomPerfil(rs.getString("nomPerfil"));
                    obj.setDescripcionPerfil(rs.getString("descripcionPerfil"));
                    obj.setIdModulo(rs.getString("idModulo"));
                    obj.setEstadoPerfil(rs.getString("estadoPerfil"));
                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error listarPerfilIdMod: " + e);
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
                System.out.println("Error listarPerfilIdMod: " + e);
            }
        }

        return null;
    }

    public ArrayList<Perfil> listarPerfilIdNom(Perfil obj) {

        ArrayList<Perfil> listar = new ArrayList();

        try {
            consulta = "Select * from perfil p inner join modulo m on p.idModulo = m.idModulo where idPerfil like ? or nomPerfil like ? order by P.idModulo ASC;";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdPerfil() + "%");
            pst.setString(2, obj.getNomPerfil() + "%");

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                for (int i = listar.size(); rs.next(); i++) {

                    obj = new Perfil();
                    //Obtiene los datos de la base de datos
                    obj.setIdPerfil(rs.getString("idPerfil"));
                    obj.setCodigoPerfil(rs.getString("codigoPerfil"));
                    obj.setNomPerfil(rs.getString("nomPerfil"));
                    obj.setDescripcionPerfil(rs.getString("descripcionPerfil"));
                    obj.setIdModulo(rs.getString("idModulo"));
                    obj.setNomModulo(rs.getString("nomModulo"));
                    obj.setEstadoPerfil(rs.getString("estadoPerfil"));
                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error listarPerfilIdNom: " + e);
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
                System.out.println("Error listarPerfilIdNom: " + e);
            }
        }

        return null;
    }

    public boolean existenciaPerfil(Perfil obj) {
        try {
            consulta = "Select * from perfil where codigoPerfil =?";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getCodigoPerfil());

            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error existenciaPerfil: " + e);
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
                System.out.println("Error existenciaPerfil: " + e);
            }
        }

        return false;
    }

    public String listarPerfilUltimoId() {

        try {
            consulta = "Select MAX(idPerfil) from Perfil;";
            pst = getConexion().prepareStatement(consulta);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                //rs.beforeFirst();

                String ultimoIdPerfil = rs.getString("MAX(idPerfil)");

                ultimoIdPerfil = Integer.toString(Integer.parseInt(ultimoIdPerfil) + 1);
                return ultimoIdPerfil;
            }
        } catch (SQLException e) {
            System.out.println("Error listarPerfilUltimoId: " + e);
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
                System.out.println("Error listarPerfilUltimoId: " + e);
            }
        }

        return null;
    }

}
