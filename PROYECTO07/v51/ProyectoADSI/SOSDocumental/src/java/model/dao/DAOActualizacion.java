package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Actualizacion;
import model.Conexion;
import model.Fecha;

/**
 * @author Einer
 */
public class DAOActualizacion extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    Fecha fec = new Fecha();//Clase Fecha donde se utilizará el metodo sumar 5 horas

    //Metodos de Actualizacion--------------------------------------------------------
    public boolean registroActualizacion(Actualizacion obj) {

        try {
            String registro = "insert into Actualizacion (idDocumento, fechaVersion, idUsuario, documento, versionActualizacion, tipoDocumento, descripcionActualizacion) Values(?,?,?,?,?,?,?);";

            pst = getConexion().prepareStatement(registro);

            pst.setString(1, obj.getIdDocumento());
            pst.setString(2, obj.getFechaRegistroDocumento());
            pst.setString(3, obj.getIdUsuario());
            pst.setBlob(4, obj.getDocumento());
            pst.setString(5, obj.getVersionDocumento());
            pst.setString(6, obj.getTipoDocumento());
            pst.setString(7, obj.getDescripcionDocumento());

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error registroActualizacion: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException e) {
                System.out.println("Error registroActualizacion: " + e);
            }
        }
        return false;
    }

    public boolean actulizarActualizacion(Actualizacion obj) {

        try {
            String actualizacion = "update Actualizacion set fechaVersion = ?, idUsuario = ?, documento = ?, versionActualizacion = ?, tipoDocumento = ?, descripcionActualizacion = ? where idDocumento = ?;";
            pst = getConexion().prepareStatement(actualizacion);

            pst.setString(1, obj.getFechaRegistroDocumento());
            pst.setString(2, obj.getIdUsuario());
            pst.setBlob(3, obj.getDocumento());
            pst.setString(4, obj.getVersionDocumento());
            pst.setString(5, obj.getTipoDocumento());
            pst.setString(6, obj.getDescripcionDocumento());
            pst.setString(7, obj.getIdDocumento());

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error actulizarActualizacion: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error actulizarActualizacion: " + e);
            }
        }
        return false;
    }

    public ArrayList<Actualizacion> listarActualizacionId(Actualizacion obj) {

        ArrayList<Actualizacion> listar = new ArrayList();

        try {
            consulta = "Select * from Actualizacion inner join documento on documento.idDocumento = actualizacion.idDocumento where actualizacion.idDocumento = ?";

            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, obj.getIdDocumento());

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                while (rs.next()) {

                    obj = new Actualizacion();

                    obj.setIdDocumento(rs.getString("idDocumento"));
                    obj.setNomDocumento(rs.getString("nomDocumento"));
                    obj.setFechaRegistroDocumento(fec.sumaHoras(rs.getString("fechaVersion")));
                    obj.setIdUsuario(rs.getString("actualizacion.idUsuario"));
                    obj.setDocumento(rs.getBinaryStream("actualizacion.documento"));
                    obj.setVersionDocumento(rs.getString("versionActualizacion"));
                    obj.setTipoDocumento(rs.getString("actualizacion.tipoDocumento"));
                    obj.setDescripcionDocumento(rs.getString("descripcionActualizacion"));

                    listar.add(obj);
                }
                return listar;
                //return informacion;
            }

        } catch (SQLException e) {
            System.out.println("Error listarActualizacion: " + e);
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
                System.out.println("Error ListarActualizacion: " + e);
            }
        }
        return null;
    }

    public boolean eliminarActualizacion(Actualizacion obj) {
        try {
            String eliminar = "delete from actualizacion where idDocumento = ?;";

            pst = getConexion().prepareStatement(eliminar);

            pst.setString(1, obj.getIdDocumento());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error eliminarActualizacion: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error eliminarActualizacion: " + e);
            }
        }
        return false;
    }

    public ArrayList<Actualizacion> listarActualizacionFecha(Actualizacion obj) {

        ArrayList<Actualizacion> listar = new ArrayList();

        try {
            consulta = "Select * from Actualizacion inner join documento on documento.idDocumento = actualizacion.idDocumento where actualizacion.fechaVersion <= ?";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getFechaRegistroDocumento());
            // pst.setString(2, this.fecha);

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();
                while (rs.next()) {

                    obj = new Actualizacion();

                    obj.setIdDocumento(rs.getString("idDocumento"));
                    obj.setNomDocumento(rs.getString("nomDocumento"));
                    obj.setFechaRegistroDocumento(fec.sumaHoras(rs.getString("fechaVersion")));
                    obj.setIdUsuario(rs.getString("actualizacion.idUsuario"));
                    obj.setDocumento(rs.getBinaryStream("actualizacion.documento"));
                    obj.setVersionDocumento(rs.getString("versionActualizacion"));
                    obj.setTipoDocumento(rs.getString("actualizacion.tipoDocumento"));
                    obj.setDescripcionDocumento(rs.getString("descripcionActualizacion"));
                    obj.setEstadoDocumento(rs.getString("estadoDocumento"));

                    listar.add(obj);
                }
                return listar;
                //return informacion;
            }

        } catch (SQLException e) {
            System.out.println("Error listarActualizacionFecha: " + e);
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
                System.out.println("Error listarActualizacionFecha: " + e);
            }
        }
        return null;
    }

}
