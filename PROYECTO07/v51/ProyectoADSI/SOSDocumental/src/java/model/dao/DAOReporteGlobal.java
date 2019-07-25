package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Fecha;
import model.ReporteGlobal;

/**
 * @author Einer
 */
public class DAOReporteGlobal extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    Fecha fec = new Fecha();

    //Metodos de Reporte Global--------------------------------------------------------
    public boolean registroRG(ReporteGlobal obj) {

        try {
            consulta = "insert into reporteglobal(idReporteGlobal, idDocumento, cantidadDescarga, cantidadBusqueda, fechaReporteGlobal) values(?,?,?,?,?);";

            pst = getConexion().prepareStatement(consulta);

            pst.setInt(1, 0);
            pst.setString(2, obj.getIdDocumento());
            pst.setString(3, "0");
            pst.setString(4, "0");
            pst.setString(5, obj.getFechaReporteGlobal());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registroReporteGlobal: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException e) {
                System.out.println("Error registroReporteGlobal: " + e);
            }
        }
        return false;
    }

    public ArrayList<ReporteGlobal> listarReporteGlobal(ReporteGlobal obj) {

        ArrayList<ReporteGlobal> listar = new ArrayList();

        try {
            consulta = "Select * from reporteGlobal where idDocumento = ?";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdDocumento());

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                while (rs.next()) {
                    obj = new ReporteGlobal();
                    obj.setCantidadBusquedas(rs.getString("cantidadBusqueda"));
                    obj.setCantidadDescargas(rs.getString("cantidadDescarga"));
                    obj.setIdDocumento(rs.getString("idDocumento"));
                    
                    listar.add(obj);
                }
                return listar;
            }

        } catch (SQLException e) {
            System.out.println("Error consultaReporteGlobal: " + e);
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
                System.out.println("Error consultaReporteGlobal: " + e);
            }
        }
        return null;
    }

    public boolean actualizarBusquedasRG(ReporteGlobal obj) {

        try {
            consulta = "update reporteGlobal set cantidadBusqueda = ? where idDocumento = ?;";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getCantidadBusquedas());
            pst.setString(2, obj.getIdDocumento());

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error actualizarBusquedasReporteGlobal: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error actualizarBusquedasReporteGlobal: " + e);
            }
        }
        return false;
    }

    public boolean actualizarDescargasRG(ReporteGlobal obj) {
        try {
            consulta = "update reporteGlobal set cantidadDescarga = ? where idDocumento = ?;";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getCantidadDescargas());
            pst.setString(2, obj.getIdDocumento());

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error actualizarDescargasReporteGlobal: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error actualizarDescargasReporteGlobal: " + e);
            }
        }
        return false;
    }
    
    public ArrayList<ReporteGlobal> listarRGFechaIdDocumento(String idDocumento, String fechaIni, String fechaFin) {

        ArrayList<ReporteGlobal> listar = new ArrayList();
        try {
            consulta = "Select * from reporteGlobal where idDocumento like ? and (fechaReporteGlobal >= ? and fechaReporteGlobal <= ?) order by idDocumento asc;";

            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, idDocumento + "%");
            pst.setString(2, fechaIni + "%");
            pst.setString(3, fechaFin + "%");
            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                //datos.clear();//Limpiar el arraylist 
                rs.beforeFirst();//Sirve para no perder el primer registro

                while (rs.next()) {
                    ReporteGlobal obj = new ReporteGlobal();

                    obj.setIdReporteGlobal(rs.getString("idReporteGlobal"));
                    obj.setIdDocumento(rs.getString("idDocumento"));
                    obj.setCantidadDescargas(rs.getString("cantidadDescarga"));
                    obj.setCantidadBusquedas(rs.getString("cantidadBusqueda"));
                    obj.setFechaReporteGlobal(fec.sumaHoras(rs.getString("fechaReporteGlobal")));

                    listar.add(obj);
                }
                return listar;
            }

        } catch (SQLException e) {
            System.out.println("Error consultaReporteGlobal: " + e);
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
                System.out.println("Error consultaReporteGlobal: " + e);
            }
        }
        return null;
    }

}


