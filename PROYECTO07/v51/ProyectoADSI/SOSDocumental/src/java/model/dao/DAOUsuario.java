package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;
import model.Conexion;

/**
 * @author Einer
 */
public class DAOUsuario extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;
    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    //Metodos de Usuario--------------------------------------------------------
    public boolean registrarUsuario(Usuario obj) {

        try {
            consulta = "insert into usuario (idUsuario, primNombre, segNombre, primApellido, segApellido, correo, idRol, contrasena, estadoUsuario) Values(?,?,?,?,?,?,?,?,?);";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdUsuario());
            pst.setString(2, obj.getPrimNombre());
            pst.setString(3, obj.getSegNombre());
            pst.setString(4, obj.getPrimApellido());
            pst.setString(5, obj.getSegApellido());
            pst.setString(6, obj.getCorreo());
            pst.setString(7, obj.getIdRol());
            pst.setString(8, obj.getContrasena());
            pst.setString(9, obj.getEstadoUsuario());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registroUsuario: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error registroUsuario: " + e);
            }
        }
        return false;
    }

    public ArrayList<Usuario> listarUsuarioIdNom(Usuario obj) {

        ArrayList<Usuario> listar = new ArrayList();

        try {
            consulta = "Select * from Usuario where idUsuario like ? or primNombre like ? or segNombre like ?;";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdUsuario() + "%");
            pst.setString(2, obj.getPrimNombre() + "%");
            pst.setString(3, obj.getPrimNombre() + "%");
            
            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                while (rs.next()) {
                    obj = new Usuario();
                    //Obtiene los datos de la base de datos        
                    obj.setIdUsuario(rs.getString("idUsuario"));
                    obj.setPrimNombre(rs.getString("primNombre"));
                    obj.setSegNombre(rs.getString("segNombre"));
                    obj.setPrimApellido(rs.getString("primApellido"));
                    obj.setSegApellido(rs.getString("segApellido"));
                    obj.setCorreo(rs.getString("correo"));
                    obj.setIdRol(rs.getString("idRol"));
                    obj.setEstadoUsuario(rs.getString("estadoUsuario"));

                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarUsuario: " + e);
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
                System.out.println("Error ListarUsuario: " + e);
            }
        }
        return null;
    }

    public boolean actualizarUsuario(Usuario obj, String idUsuario) {

        try {
            consulta = "update usuario set idUsuario=?, primNombre=?, segNombre=?, primApellido=?, segApellido=?, correo=?, idRol=?, estadoUsuario=? where idUsuario=?;";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdUsuario());
            pst.setString(2, obj.getPrimNombre());
            pst.setString(3, obj.getSegNombre());
            pst.setString(4, obj.getPrimApellido());
            pst.setString(5, obj.getSegApellido());
            pst.setString(6, obj.getCorreo());
            pst.setString(7, obj.getIdRol());
            pst.setString(8, obj.getEstadoUsuario());
            pst.setString(9, idUsuario);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error actualizarUsuario: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error actualizarUsuario: " + e);
            }
        }
        return false;
    }

    public ArrayList<Usuario> listarUsuarioId(Usuario obj) {

        ArrayList<Usuario> listar = new ArrayList();

        try {
            consulta = "Select * from Usuario where idUsuario = ?";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdUsuario());

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                while (rs.next()) {
                    obj = new Usuario();
                    //Obtiene los datos de la base de datos        
                    obj.setIdUsuario(rs.getString("idUsuario"));
                    obj.setPrimNombre(rs.getString("primNombre"));
                    obj.setSegNombre(rs.getString("segNombre"));
                    obj.setPrimApellido(rs.getString("primApellido"));
                    obj.setSegApellido(rs.getString("segApellido"));
                    obj.setCorreo(rs.getString("correo"));
                    obj.setIdRol(rs.getString("idRol"));
                    obj.setEstadoUsuario(rs.getString("estadoUsuario"));

                    listar.add(obj);
                }
                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarUsuarioID: " + e);
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
                System.out.println("Error ListarUsuarioID: " + e);
            }
        }
        return null;
    }

    public boolean validarCorreo(Usuario obj) {

        ArrayList<ArrayList> listar = new ArrayList();
        try {
            consulta = "Select * from usuario where correo = ?;";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getCorreo());

            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarUsuarioCorro: " + e);
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
                System.out.println("Error ListarUsuarioCorro: " + e);
            }
        }
        return false;
    }

    public ArrayList<Usuario> autenticarUsuario(Usuario obj) {

        ArrayList<Usuario> listar = new ArrayList();

        try {
            consulta = "Select * from Usuario where idUsuario = ? and contrasena = ?";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdUsuario());
            pst.setString(2, obj.getContrasena());

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                while (rs.next()) {
                    obj = new Usuario();
                    //Obtiene los datos de la base de datos        
                    obj.setIdUsuario(rs.getString("idUsuario"));
                    obj.setPrimNombre(rs.getString("primNombre"));
                    obj.setSegNombre(rs.getString("segNombre"));
                    obj.setPrimApellido(rs.getString("primApellido"));
                    obj.setSegApellido(rs.getString("segApellido"));
                    obj.setCorreo(rs.getString("correo"));
                    obj.setIdRol(rs.getString("idRol"));
                    obj.setEstadoUsuario(rs.getString("estadoUsuario"));

                    listar.add(obj);
                }
                return listar;
            }

        } catch (SQLException e) {
            System.out.println("Error autenticacionUsuario: " + e);
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
                System.out.println("Error autenticacionUsuario: " + e);
            }
        }
        return null;
    }

    public boolean autenticarRecuperacionContrasena(Usuario obj) {

        try {
            consulta = "Select * from Usuario where idUsuario= ? and correo = ?";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdUsuario());
            pst.setString(2, obj.getCorreo());

            rs = pst.executeQuery();
            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error autenticarCambioContrasena: " + e);
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
                System.out.println("Error autenticarCambioContrasena: " + e);
            }
        }
        return false;
    }

    public boolean modificarContrasena(Usuario obj) {

        try {
            consulta = "update Usuario set contrasena = ? where idUsuario = ?";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getContrasena());
            pst.setString(2, obj.getIdUsuario());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error cambioContrasena: " + e);
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
                System.out.println("Error cambioContrasena: " + e);
            }
        }
        return false;
    }

}
