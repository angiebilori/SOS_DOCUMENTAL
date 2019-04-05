package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Einer
 */
public class usuario extends conexion {

    //Definicion de variables privadas - atributos
    private String idUsuario;
    private String primNombre;
    private String segNombre;
    private String primApellido;
    private String segApellido;
    private String email;
    private String idRol;
    private String rol;
    private String contrasenia;

    //Constructores - encapsulacion de datos
    public usuario(String idUsuario, String primNombre, String segNombre, String primApellido, String segApellido, String email, String idRol, String rol, String contrasenia) {
        this.idUsuario = idUsuario;
        this.primNombre = primNombre;
        this.segNombre = segNombre;
        this.primApellido = primApellido;
        this.segApellido = segApellido;
        this.email = email;
        this.idRol = idRol;
        this.rol = rol;
        this.contrasenia = contrasenia;
    }

    public usuario() {
    }

    //Getters y setters obtener y asignar informacion a las variables
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPrimNombre() {
        return primNombre;
    }

    public void setPrimNombre(String primNombre) {
        this.primNombre = primNombre;
    }

    public String getSegNombre() {
        return segNombre;
    }

    public void setSegNombre(String segNombre) {
        this.segNombre = segNombre;
    }

    public String getPrimApellido() {
        return primApellido;
    }

    public void setPrimApellido(String primApellido) {
        this.primApellido = primApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public void setSegApellido(String segApellido) {
        this.segApellido = segApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdrol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    //Variables de conexion
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = getConexion();

    //Metodos de usuario--------------------------------------------------------
    public boolean registroUsuario() {

        try {
            String registro = "insert into usuario (idUsuario, primNombre, segNombre, primApellido, segApellido, email, idRol, Password) Values(?,?,?,?,?,?,?,?)";
            pst = getConexion().prepareStatement(registro);

            pst.setString(1, this.idUsuario);
            pst.setString(2, this.primNombre);
            pst.setString(3, this.segNombre);
            pst.setString(4, this.primApellido);
            pst.setString(5, this.segApellido);
            pst.setString(6, this.email);
            pst.setString(7, this.idRol);
            pst.setString(8, this.contrasenia);

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

    public boolean autenticacionUsuario() {

        try {
            String consulta = "Select * from usuario where idUsuario = ? and password = ?";
            pst = conn.prepareStatement(consulta);
            pst.setString(1, this.idUsuario);
            pst.setString(2, this.contrasenia);

            rs = pst.executeQuery();
            
            if (rs.absolute(1)) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error autenticacionUsuario: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
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
        return false;
    }

    public boolean datosUsuario() {

        try {
            String consulta = "Select * from usuario inner join rol on usuario.idRol=rol.idRol where idUsuario = ?";
            pst = conn.prepareStatement(consulta);
            pst.setString(1, this.idUsuario);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                //Obtiene los datos de la base de datos
                this.idUsuario = rs.getString("idUsuario");
                this.primNombre = rs.getString("primNombre");
                this.segNombre = rs.getString("segNombre");
                this.primApellido = rs.getString("primApellido");
                this.segApellido = rs.getString("segApellido");
                this.email = rs.getString("email");
                this.rol = rs.getString("cargo");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error datosUsuario: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException e) {
                System.out.println("Error datosUsuario: " + e);
            }
        }
        return false;
    }

    public boolean modificarUsuario() {
        return false;
    }

    public boolean eliminarUsuario() {
        return false;
    }

    public boolean autenticarRecuperacionContrasenia() {

        try {
            String consulta = "Select * from usuario where idUsuario= ? and email = ?";
            pst = conn.prepareStatement(consulta);
            pst.setString(1, this.idUsuario);
            pst.setString(2, this.email);
            rs = pst.executeQuery();
            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error autenticarCambioContrasena: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
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

    public boolean modificarContrasena() {

        try {
            String consultau = "update usuario set password = ? where idUsuario = ?";
            pst = conn.prepareStatement(consultau);
            pst.setString(1, this.contrasenia);
            pst.setString(2, this.idUsuario);
            //rs = pst.executeQuery();

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error cambioContrasena: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
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
