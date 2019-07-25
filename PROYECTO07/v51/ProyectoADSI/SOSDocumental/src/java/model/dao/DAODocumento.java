package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Documento;
import model.Fecha;

/**
 * @author Einer
 */
public class DAODocumento extends Conexion {

    //Variable donde se almacenará las consultas MYSQL
    String consulta;

    //varables pst y rs
    private PreparedStatement pst;
    private ResultSet rs;

    Fecha fec = new Fecha();//Clase Fecha donde se utilizará el metodo sumar 5 horas

    //Metodos de Documento--------------------------------------------------------
    public boolean registroDocumento(Documento obj) {

        try {
            consulta = "insert into Documento (idDocumento, nomDocumento, fechaRegistroDocumento, idUsuario, documento, versionDocumento, tipoDocumento, estadoDocumento, descripcionDocumento) Values(?,?,?,?,?,?,?,?,?);";

            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdDocumento());
            pst.setString(2, obj.getNomDocumento());
            pst.setString(3, obj.getFechaRegistroDocumento());
            pst.setString(4, obj.getIdUsuario());
            pst.setBlob(5, obj.getDocumento());
            pst.setString(6, obj.getVersionDocumento());
            pst.setString(7, obj.getTipoDocumento());
            pst.setString(8, obj.getEstadoDocumento());
            pst.setString(9, obj.getDescripcionDocumento());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registroDocumento: " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error registroDocumento: " + e);
            }
        }
        return false;
    }

    public ArrayList<Documento> listarDocumentoIdNom(Documento obj) {

        ArrayList<Documento> listar = new ArrayList();

        try {
            consulta = "Select * from documento where idDocumento like ? or nomDocumento like ?";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, "%" + obj.getIdDocumento() + "%");
            pst.setString(2, obj.getNomDocumento() + "%");

            //pst.setString(2, nomDocumento);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {

                    obj = new Documento();

                    //Obtiene los datos de la base de datos
                    obj.setIdDocumento(rs.getString("idDocumento"));
                    obj.setNomDocumento(rs.getString("nomDocumento"));
                    obj.setFechaRegistroDocumento(fec.sumaHoras(rs.getString("fechaRegistroDocumento")));
                    obj.setDescripcionDocumento(rs.getString("descripcionDocumento"));

                    /*
                    listar.get(i).add(rs.getString("idDocumento"));
                    listar.get(i).add(rs.getString("nomDocumento"));
                    listar.get(i).add(fec.sumaHoras(rs.getString("fechaRegistroDocumento")));
                    listar.get(i).add(rs.getString("descripcionDocumento"));
                     */
                    listar.add(obj);
                }

                return listar;
            }
        } catch (SQLException e) {
            System.out.println("Error ListarDocumento: " + e);
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
                System.out.println("Error ListarDocumento: " + e);
            }
        }
        return null;
    }

    public boolean actualizarDocumento(Documento obj, String idDoc) {
        try {
            consulta = "update documento set idDocumento = ?, nomDocumento = ?, fechaRegistroDocumento = ?, idUsuario = ?, documento = ?, versionDocumento = ?, tipoDocumento = ?, estadoDocumento = ?, descripcionDocumento = ? where idDocumento = ?;";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdDocumento());
            pst.setString(2, obj.getNomDocumento());
            pst.setString(3, obj.getFechaRegistroDocumento());
            pst.setString(4, obj.getIdUsuario());
            pst.setBlob(5, obj.getDocumento());
            pst.setString(6, obj.getVersionDocumento());
            pst.setString(7, obj.getTipoDocumento());
            pst.setString(8, obj.getEstadoDocumento());
            pst.setString(9, obj.getDescripcionDocumento());
            pst.setString(10, idDoc);

            //Actualiza con la nueva version
            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Actualizar documento" + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException e) {
                System.out.println("Error Actualizar documento" + e);
            }
        }
        return false;
    }

    public boolean actualizarDocumentoInfo(Documento obj, String idDoc) {
        try {
            consulta = "update documento set idDocumento = ?, nomDocumento = ?, estadoDocumento = ?, descripcionDocumento = ? where idDocumento = ?;";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdDocumento());
            pst.setString(2, obj.getNomDocumento());
            pst.setString(3, obj.getEstadoDocumento());
            pst.setString(4, obj.getDescripcionDocumento());
            pst.setString(5, idDoc);

            //Actualiza con los datos version
            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Actualizar documento" + e);
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException e) {
                System.out.println("Error Actualizar documento" + e);
            }
        }
        return false;
    }

    public boolean elimianrDocumento(Documento obj) {
        return false;
    }

    public ArrayList<Documento> listarDocumentoId(Documento obj) {

        ArrayList<Documento> listar = new ArrayList();

        try {
            consulta = "Select * from documento where idDocumento = ?";
            pst = getConexion().prepareStatement(consulta);

            pst.setString(1, obj.getIdDocumento());
            //pst.setString(2, nomDocumento);

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                rs.beforeFirst();

                while (rs.next()) {

                    obj = new Documento();
                    obj.setIdDocumento(rs.getString("idDocumento"));
                    obj.setNomDocumento(rs.getString("nomDocumento"));
                    obj.setFechaRegistroDocumento(fec.sumaHoras(rs.getString("fechaRegistroDocumento")));
                    obj.setIdUsuario(rs.getString("idUsuario"));
                    obj.setDocumento(rs.getBinaryStream("documento"));
                    obj.setVersionDocumento(rs.getString("versionDocumento"));
                    obj.setTipoDocumento(rs.getString("tipoDocumento"));
                    obj.setEstadoDocumento(rs.getString("estadoDocumento"));
                    obj.setDescripcionDocumento(rs.getString("descripcionDocumento"));

                    listar.add(obj);
                }

                // informacion.add(new Documento());
                return listar;
                //return informacion;
            }

        } catch (SQLException e) {
            System.out.println("Error ListarDocumentoID: " + e);
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
                System.out.println("Error ListarDocumentoID: " + e);
            }
        }
        return null;
    }

}
