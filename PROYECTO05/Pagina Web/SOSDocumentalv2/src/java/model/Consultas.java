package model;

import controller.autenticarServlet;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Para obtener la fecha y hora
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consultas extends Conexion {

    public static void main(String[] args) {
        Consultas co = new Consultas();
        //System.out.println(co.autenticacion(1030647666, "Einer1030647666")); // metodo para autenticar
        //System.out.println(co.registrar(322, "Loaisa",null, "Rico", "daz","ennssdj@gmail.com", 1, "Loaisa123")); //Metode registrar
        //System.out.println(co.consultaupdate("3")); //Metodo de consulta por documento
        //System.out.println(co.cargarregistros());
        //System.out.println(co.contarfilas());
        //System.out.println(co.datosUsuario(1030647444));
        //System.out.println(co.restaurarContrasena(1030647666, "123"));
        //System.out.println(co.obtenerFechaHora());
        //System.out.println(co.registrarSesion(1030647666, "2019/03/06 19:40:50"));
    }

    /*
    public boolean obtenerFechaHora() {
        try {
            Date date = new Date();
            //Caso 1: obtener la hora y salida por pantalla con formato:
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            System.out.println("Hora: " + hourFormat.format(date));
            //Caso 2: obtener la fecha y salida por pantalla con formato:
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            System.out.println("Fecha: " + dateFormat.format(date));
            //Caso 3: obtenerhora y fecha y salida por pantalla con formato:
            DateFormat hourdateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
            System.out.println("Hora y fecha: " + hourdateFormat.format(date));
            return true;
        } catch (Exception e) {

        }
        return false;
    }
     */
    //extends: Ereda todo los metodos(TODO) de la clase conexion
    public boolean autenticacion(int usuario, String password) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "Select * from usuario where idUsuario= ? and password = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, usuario);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.absolute(1)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
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
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }

    public boolean datosUsuario(int documento) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consultau = "Select usuario.primNombre, usuario.primApellido, rol.cargo from usuario inner join rol on usuario.idRol=rol.idRol where idUsuario = ?";
            pst = getConexion().prepareStatement(consultau);
            pst.setInt(1, documento);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                //Verifica la consulta a través del llamado de un registro de la tabla
                //System.out.println(rs.getString("primNombre") + " " + rs.getString("primApellido") + " " + rs.getString("cargo"));

                autenticarServlet.nombre = rs.getString("primNombre");
                autenticarServlet.apellido = rs.getString("primApellido");
                autenticarServlet.rol = rs.getString("cargo");

                //System.out.println(autenticarServlet.nombre + " " + autenticarServlet.apellido + " " + autenticarServlet.rol);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
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

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }

    public boolean registrar(int documento, String primNombre, String segNombre, String primApellido, String segApellido, String email, int idRol, String password) {
        PreparedStatement pst = null;
        try {
            String registro = "insert into usuario (idUsuario, primNombre, segNombre, primApellido, segApellido, email, idRol, Password) Values(?,?,?,?,?,?,?,?)";
            pst = getConexion().prepareStatement(registro);

            pst.setInt(1, documento);
            pst.setString(2, primNombre);
            pst.setString(3, segNombre);
            pst.setString(4, primApellido);
            pst.setString(5, segApellido);
            pst.setString(6, email);
            pst.setInt(7, idRol);
            pst.setString(8, password);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }

    public boolean autenticarCambioContrasena(int usuario, String email) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "Select * from usuario where idUsuario= ? and email = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, usuario);
            pst.setString(2, email);
            rs = pst.executeQuery();
            if (rs.absolute(1)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
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
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }

    public boolean restaurarContrasena(int documento, String nuevaContrasena) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consultau = "update usuario set password = ? where idUsuario = ?";
            pst = getConexion().prepareStatement(consultau);
            pst.setString(1, nuevaContrasena);
            pst.setInt(2, documento);
            //rs = pst.executeQuery();

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
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

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }

    public boolean registrarPdf(int idDocumento, String nomDocumento, String fechaRegistro, int idUsuario, InputStream documento, String version) {
        PreparedStatement pst = null;
        try {
            String registro = "insert into Documento (idDocumento, nomDocumento, fechaRegistro, idUsuario, documento,version) Values(?,?,?,?,?,?);";
            pst = getConexion().prepareStatement(registro);

            pst.setInt(1, idDocumento);
            pst.setString(2, nomDocumento);
            pst.setString(3, fechaRegistro);
            pst.setInt(4, idUsuario);
            pst.setBlob(5, documento);
            pst.setString(6, version);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }
    /*
    public boolean consultaupdate(String doc) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consultau = "Select * from aprendiz where docAprendiz = ?";
            pst = getConexion().prepareStatement(consultau);
            pst.setString(1, doc);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                //Verifica la consulta a través del llamado de un registro de la tabla
                // System.out.println(rs.getString("nombreAprendiz") + " " + rs.getString("apellidoaprendiz"));

                ConsultaI.nom = rs.getString("nombreAprendiz");
                ConsultaI.apll = rs.getString("apellidoaprendiz");
                ConsultaI.correo = rs.getString("correoaprendiz");
                ConsultaI.telefono = rs.getString("telefonoaprendiz");
                ConsultaI.usu = rs.getString("usuarioaprendiz");

                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
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

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }

    public static String[] tabla1 = new String[ConsultaM.count];

    public boolean cargarregistros() {
        PreparedStatement pst = null;
        ResultSet rs = null;

        String[] tabla = new String[ConsultaM.count];
        tabla1 = tabla;

        try {
            String consulta = "Select * from aprendiz";
            pst = getConexion().prepareStatement(consulta);
            rs = pst.executeQuery();

            int i = 0;

            while (rs.next()) {
                tabla[i] = (rs.getString("docAprendiz"));
                i++;
                tabla[i] = (rs.getString("nombreAprendiz"));
                i++;
                tabla[i] = (rs.getString("apellidoAprendiz"));
                i++;
                tabla[i] = (rs.getString("correoAprendiz"));
                i++;
                tabla[i] = (rs.getString("telefonoAprendiz"));
                i++;
                tabla[i] = (rs.getString("usuarioAprendiz"));
                i++;
            }
            return true;

        } catch (Exception e) {
            System.out.println("Error: " + e);
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
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }
     */
    public static int count1;

    public boolean contarfilas() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 1;
        count = count - 2;
        Conexion conn = new Conexion();
        try {
            String contar = "select count(*) from gestionaprendices.aprendiz;";
            pst = conn.getConexion().prepareStatement(contar);
            rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            count = count * 6;
            count1 = count;
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (conn.getConexion() != null) {
                    conn.getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return false;
    }
}