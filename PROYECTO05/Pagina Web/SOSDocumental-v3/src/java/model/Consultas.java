package model;

import controller.agregarPdfServlet;
import controller.autenticarServlet;
import controller.restaurarContrasenaServlet;
import controller.cambiarContrasenaServlet;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Para obtener la fecha y hora
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Consultas extends Conexion {

    public static void main(String[] args) {
        Consultas co = new Consultas();
        System.out.println(co.actualizarAuto());
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
    public boolean autenticacion(String usuario, String password) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "Select * from usuario where idUsuario= ? and password = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, usuario);
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

    public boolean datosUsuario(String documento) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consultau = "Select usuario.primNombre, usuario.primApellido, rol.cargo, usuario.email from usuario inner join rol on usuario.idRol=rol.idRol where idUsuario = ?";
            pst = getConexion().prepareStatement(consultau);
            pst.setString(1, documento);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                //Verifica la consulta a través del llamado de un registro de la tabla
                //System.out.println(rs.getString("primNombre") + " " + rs.getString("primApellido") + " " + rs.getString("cargo"));

                autenticarServlet.nombre = rs.getString("primNombre");
                autenticarServlet.apellido = rs.getString("primApellido");
                autenticarServlet.rol = rs.getString("cargo");

                restaurarContrasenaServlet.nombre = rs.getString("primNombre");
                restaurarContrasenaServlet.apellido = rs.getString("primApellido");

                cambiarContrasenaServlet.nombre = rs.getString("primNombre");
                cambiarContrasenaServlet.apellido = rs.getString("primApellido");
                cambiarContrasenaServlet.email = rs.getString("email");

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

    public boolean autenticarCambioContrasena(String usuario, String email) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "Select * from usuario where idUsuario= ? and email = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, usuario);
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

    public boolean restaurarContrasena(String documento, String nuevaContrasena) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consultau = "update usuario set password = ? where idUsuario = ?";
            pst = getConexion().prepareStatement(consultau);
            pst.setString(1, nuevaContrasena);
            pst.setString(2, documento);
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

    public boolean registrarPdf(int idDocumento, String nomDocumento, String fechaRegistro, String idUsuario, InputStream documento, int version) {
        PreparedStatement pst = null;
        try {
            String registro = "insert into Documento (idDocumento, nomDocumento, fechaRegistro, idUsuario, documento,version) Values(?,?,?,?,?,?);";
            String reporte = "insert into reporteEspecifico (idReporteEspecifico, idDocumento, version, fechaVersion, idUsuario) Values(?,?,?,?,?);";

            pst = getConexion().prepareStatement(registro);

            pst.setInt(1, idDocumento);
            pst.setString(2, nomDocumento);
            pst.setString(3, fechaRegistro);
            pst.setString(4, idUsuario);
            pst.setBlob(5, documento);
            pst.setInt(6, version);

            if (pst.executeUpdate() == 1) {
                pst = null;

                pst = getConexion().prepareStatement(reporte);

                pst.setInt(1, 0);
                pst.setInt(2, idDocumento);
                pst.setInt(3, version);
                pst.setString(4, fechaRegistro);
                pst.setString(5, idUsuario);

                if (pst.executeUpdate() == 1) {
                    return true;
                }
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

    public boolean actualizarDocumento(int idDocumento, String fechaVersion, String idUsuario, InputStream documento) {

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String consulta = "Select idDocumento, fechaVersion, idUsuario, documento, version from Actualizacion where idDocumento = ?;";
            pst = getConexion().prepareStatement(consulta);

            pst.setInt(1, idDocumento);

            rs = pst.executeQuery();
            if (rs.absolute(1)) {

                //Obtener datos que se insertaran
                int idDocumentoo = rs.getInt("idDocumento");
                Date fec = rs.getTimestamp("fechaVersion");
                int idUsuarioo = rs.getInt("idUsuario");
                Blob documentoo = rs.getBlob("documento");
                int versionn = rs.getInt("version");

                int versionf = versionn + 1;

                //Sumar 5 horas (ya que aparece 5 horas mas temprano)
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(fec);
                calendar.add(Calendar.HOUR, 05);
                fec = calendar.getTime();

                //Darle formato a las horas
                DateFormat hourdateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //Formato de fecha y hora
                String fechaa = hourdateFormat.format(fec);

                //Si ya existe una version pendiente la inserta en documento
                pst = null;
                String insercion = "update documento set fechaRegistro = ?, idUsuario = ?, documento = ?, version = ? where idDocumento = ?;";
                pst = getConexion().prepareStatement(insercion);

                pst.setString(1, fechaa);
                pst.setInt(2, idUsuarioo);
                pst.setBlob(3, documentoo);
                pst.setInt(4, versionn);
                pst.setInt(5, idDocumentoo);

                //Actualiza con la nueva version
                if (pst.executeUpdate() == 1) {

                    pst = null;
                    String actualizacion = "update Actualizacion set fechaVersion = ?, idUsuario = ?, documento = ?, version = ? where idDocumento = ?;";
                    pst = getConexion().prepareStatement(actualizacion);

                    pst.setString(1, fechaVersion);
                    pst.setString(2, idUsuario);
                    pst.setBlob(3, documento);
                    pst.setInt(4, versionf);
                    pst.setInt(5, idDocumentoo);

                    if (pst.executeUpdate() == 1) {

                        //Actualiza la tabla de reportes
                        pst = null;
                        String reporte = "insert into reporteEspecifico (idReporteEspecifico, idDocumento, version, fechaVersion, idUsuario) Values(?,?,?,?,?);";

                        pst = getConexion().prepareStatement(reporte);

                        pst.setInt(1, 0);
                        pst.setInt(2, idDocumentoo);
                        pst.setInt(3, versionf);
                        pst.setString(4, fechaVersion);
                        pst.setString(5, idUsuario);

                        if (pst.executeUpdate() == 1) {
                            return true;
                        }
                    }
                }
            } else {
//inertar documento 
                pst = null;
                String consultaa = "Select version from Documento where idDocumento = ?;";
                pst = getConexion().prepareStatement(consultaa);

                pst.setInt(1, idDocumento);

                rs = pst.executeQuery();
                if (rs.absolute(1)) {

                    //Obtener datos que se insertaran
                    int versionn = rs.getInt("version");

                    int versionf = versionn + 1;

                    pst = null;

                    String registro = "insert into Actualizacion (idDocumento, fechaVersion, idUsuario, documento,version) Values(?,?,?,?,?);";

                    pst = getConexion().prepareStatement(registro);

                    pst.setInt(1, idDocumento);
                    pst.setString(2, fechaVersion);
                    pst.setString(3, idUsuario);
                    pst.setBlob(4, documento);
                    pst.setInt(5, versionf);

                    if (pst.executeUpdate() == 1) {

                        //Actualiza la tabla de reportes
                        pst = null;
                        String reporte = "insert into reporteEspecifico (idReporteEspecifico, idDocumento, version, fechaVersion, idUsuario) Values(?,?,?,?,?);";

                        pst = getConexion().prepareStatement(reporte);

                        pst.setInt(1, 0);
                        pst.setInt(2, idDocumento);
                        pst.setInt(3, versionf);
                        pst.setString(4, fechaVersion);
                        pst.setString(5, idUsuario);

                        if (pst.executeUpdate() == 1) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {

            agregarPdfServlet.error = "" + e;
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

    public boolean exixtenciaDocumento(int idDocumento) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "Select * from Documento where idDocumento = ?";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idDocumento);

            rs = pst.executeQuery();
            if (rs.absolute(1)) {
                return true;
            }
        } catch (Exception e) {
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

    public boolean actualizarAuto() {

        PreparedStatement pst = null;
        ResultSet rs = null;
        PreparedStatement pstc = null;
        ResultSet rsc = null;
        try {

            Date date = new Date();
            //Caso 3: obtenerhora y fecha y salida por pantalla con formato:
            DateFormat hourdateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -1);
            date = calendar.getTime();

            String fechaf = hourdateFormat.format(date);
            // System.out.println(fechaf);

            String consulta = "Select * from Actualizacion where fechaVersion <= ?;";

            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, fechaf);

            rs = pst.executeQuery();

            int count = 0;
            int count1 = 0;

            //  if (rs.absolute(1)) {
            String contar = "select count(*) from actualizacion where fechaVersion <= ?;";

            pstc = getConexion().prepareStatement(contar);
            pstc.setString(1, fechaf);

            rsc = pstc.executeQuery();
            rsc.next();
            count = rsc.getInt(1);
            count1 = count * 3;

            String[] datos = new String[count1];
            Blob[] documento = new Blob[count];
            Date[] fecha = new Date[count];
            int i = 0;
            int j = 0;

            while (rs.next()) {
                datos[i] = (rs.getString("idDocumento"));
                i++;

                datos[i] = (rs.getString("idUsuario"));
                i++;

                datos[i] = (rs.getString("version"));
                i++;
                //Sumar 5 horas (ya que aparece 5 horas mas temprano)
                fecha[j] = rs.getTimestamp("fechaVersion");
                calendar.setTime(fecha[j]);
                calendar.add(Calendar.HOUR, 05);
                fecha[j] = calendar.getTime();

                documento[j] = (rs.getBlob("documento"));
                j++;
            }

            //Darle formato a las horas
            //Si ya existe una version pendiente la inserta en documento
            int r = 0;
            int d = 0;

            while (count > 0) {

                pst = null;
                String insercion = "update documento set fechaRegistro = ?, idUsuario = ?, documento = ?, version = ? where idDocumento = ?;";
                pst = getConexion().prepareStatement(insercion);

                pst.setString(5, datos[r]);
                r++;
                pst.setString(2, datos[r]);
                r++;
                pst.setString(4, datos[r]);
                r++;

                String fechaa = hourdateFormat.format(fecha[d]);
                pst.setString(1, fechaa);

                pst.setBlob(3, documento[d]);
                d++;

                if (pst.executeUpdate() == 1) {
                    //Borrar los datos de la tabla actualizacion
                    pst = null;;
                    String eliminar = "delete from actualizacion where idDocumento = ?;";

                    pst = getConexion().prepareStatement(eliminar);
                    pst.setString(1, datos[r - 3]);
                    if (pst.executeUpdate() == 1) {
                        count--;
                    }
                }
            }
            return true;
            //}

        } catch (Exception e) {
            System.out.println(e);
        }finally {
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
