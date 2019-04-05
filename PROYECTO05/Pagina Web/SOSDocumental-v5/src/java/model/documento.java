package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Einer
 */
public class documento extends conexion {

    private String idDocumento;
    private String nomDocumento;
    private String fecha;
    private String idUsuario;
    private String version;
    private String tipoDocumento;
    private String cantDescargas;
    private String cantBusquedas;
    private InputStream documento;

    private ArrayList<String> datos = new ArrayList();
    private ArrayList<String> datosA = new ArrayList();

    public documento(String idDocumento, String nomDocumento, String fecha, String idUsuario, String version, String tipoDocumento, String cantDescargas, String cantBusquedas, InputStream documento) {
        this.idDocumento = idDocumento;
        this.nomDocumento = nomDocumento;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.version = version;
        this.tipoDocumento = tipoDocumento;
        this.cantDescargas = cantDescargas;
        this.cantBusquedas = cantBusquedas;
        this.documento = documento;
    }

    public documento() {
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNomDocumento() {
        return nomDocumento;
    }

    public void setNomDocumento(String nomDocumento) {
        this.nomDocumento = nomDocumento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCantDescargas() {
        return cantDescargas;
    }

    public void setCantDescargas(String cantDescargas) {
        this.cantDescargas = cantDescargas;
    }

    public String getCantBusquedas() {
        return cantBusquedas;
    }

    public void setCantBusquedas(String cantBusquedas) {
        this.cantBusquedas = cantBusquedas;
    }

    public InputStream getDocumento() {
        return documento;
    }

    public void setDocumento(InputStream documento) {
        this.documento = documento;
    }

    public ArrayList getDatos() {
        return datos;
    }

    public ArrayList getDatosA() {
        return datosA;
    }

    //Variables de conexion
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = getConexion();

    //Metodos tabla documento
    public boolean registroDocumento() {

        try {
            String registro = "insert into Documento (idDocumento, nomDocumento, fechaRegistro, idUsuario, documento, version, tipoDocumento) Values(?,?,?,?,?,?,?);";

            pst = conn.prepareStatement(registro);

            pst.setString(1, this.idDocumento);
            pst.setString(2, this.nomDocumento);
            pst.setString(3, this.fecha);
            pst.setString(4, this.idUsuario);
            pst.setBlob(5, this.documento);
            pst.setString(6, this.version);
            pst.setString(7, this.tipoDocumento);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registroDocumento: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
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

    public boolean actualizarDocumento() {
        try {
            String insercion = "update documento set fechaRegistro = ?, idUsuario = ?, documento = ?, version = ?, tipoDocumento = ? where idDocumento = ?;";
            pst = conn.prepareStatement(insercion);

            pst.setString(1, fecha);
            pst.setString(2, idUsuario);
            pst.setBlob(3, documento);
            pst.setString(4, version);
            pst.setString(5, tipoDocumento);
            pst.setString(6, idDocumento);

            //Actualiza con la nueva version
            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Actualizar documento" + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
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

    public boolean consultaDocumento() {

        try {
            String consulta = "Select * from documento where idDocumento = ?";
            pst = conn.prepareStatement(consulta);

            pst.setString(1, this.idDocumento);
            //pst.setString(2, nomDocumento);

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                this.idDocumento = rs.getString("idDocumento");
                this.nomDocumento = rs.getString("nomDocumento");
                this.fecha = rs.getString("fechaRegistro");
                this.idUsuario = rs.getString("idUsuario");
                this.documento = rs.getBinaryStream("documento");
                this.version = rs.getString("version");
                this.tipoDocumento = rs.getString("tipoDocumento");

                this.datos.clear();
                this.datosA.clear();

                this.datos.add(idDocumento);
                this.datos.add(nomDocumento);
                this.datos.add(fecha);
                this.datos.add(idUsuario);
                this.datos.add(version);
                this.datos.add(tipoDocumento);

                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error consultaDocumento: " + e);
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
                System.out.println("Error consultaDocumento: " + e);
            }
        }
        return false;
    }

    //Metodos tabla Actualizacion
    public boolean registroActualizacion() {

        try {

            String registro = "insert into Actualizacion (idDocumento, fechaVersion, idUsuario, documento, version, tipoDocumento) Values(?,?,?,?,?,?);";

            pst = conn.prepareStatement(registro);

            pst.setString(1, idDocumento);
            pst.setString(2, fecha);
            pst.setString(3, idUsuario);
            pst.setBlob(4, documento);
            pst.setString(5, version);
            pst.setString(6, tipoDocumento);

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error registroActualizacion: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
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

    public boolean actulizarActualizacion() {

        try {
            String actualizacion = "update Actualizacion set fechaVersion = ?, idUsuario = ?, documento = ?, version = ?, tipoDocumento = ? where idDocumento = ?;";
            pst = conn.prepareStatement(actualizacion);

            pst.setString(1, fecha);
            pst.setString(2, idUsuario);
            pst.setBlob(3, documento);
            pst.setString(4, version);
            pst.setString(5, tipoDocumento);
            pst.setString(6, idDocumento);

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error actulizarActualizacion: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
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

    public boolean consultaActualizacion() {

        try {
            String consulta = "Select * from Actualizacion inner join documento on documento.idDocumento = actualizacion.idDocumento where actualizacion.idDocumento = ? || actualizacion.fechaVersion <= ?";

            pst = conn.prepareStatement(consulta);

            pst.setString(1, this.idDocumento);
            pst.setString(2, this.fecha);

            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                this.idDocumento = rs.getString("idDocumento");
                this.nomDocumento = rs.getString("nomDocumento");
                this.fecha = rs.getString("fechaVersion");
                this.idUsuario = rs.getString("idUsuario");
                this.documento = rs.getBinaryStream("documento");
                this.version = rs.getString("actualizacion.version");
                this.tipoDocumento = rs.getString("tipoDocumento");

                this.datosA.add(idDocumento);
                this.datosA.add(nomDocumento);
                this.datosA.add(fecha);
                this.datosA.add(idUsuario);
                this.datosA.add(version);
                this.datosA.add(tipoDocumento);

                // informacion.add(new documento());
                return true;
                //return informacion;
            }

        } catch (SQLException e) {
            System.out.println("Error consultaActualizacion: " + e);
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
                System.out.println("Error consultaActualizacion: " + e);
            }
        }

        return false;
    }

    public boolean eliminarActualizacion() {
        try {
            String eliminar = "delete from actualizacion where idDocumento = ?;";

            pst = conn.prepareStatement(eliminar);

            pst.setString(1, idDocumento);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error eliminarActualizacion: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
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

    //Metodos tabla Busqueda
    public boolean registrarBusqueda() {
        try {
            String consulta = "insert into busqueda(idBusqueda, fecha, idDocumento, idUsuario) values(?,?,?,?)";

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, 0);
            pst.setString(2, this.fecha);
            pst.setString(3, this.idDocumento);
            pst.setString(4, this.idUsuario);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registrarBusqueda: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error registrarBusqueda: " + e);
            }
        }
        return false;
    }

    //Metodos tabla Descarga
    public boolean registrarDescarga() {
        try {
            String consulta = "insert into descarga(idDescarga, fecha, idDocumento, idUsuario) values(?,?,?,?)";

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, 0);
            pst.setString(2, this.fecha);
            pst.setString(3, this.idDocumento);
            pst.setString(4, this.idUsuario);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registrarDescarga: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error registrarDescarga: " + e);
            }
        }
        return false;
    }

    //Metodos tabla reporte especifico
    public boolean registroReporteEspecifico() {

        try {
            String consulta = "insert into reporteEspecifico (idReporteEspecifico, idDocumento, version, fechaVersion, idUsuario) Values(?,?,?,?,?);";

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, 0);
            pst.setString(2, idDocumento);
            pst.setString(3, version);
            pst.setString(4, fecha);
            pst.setString(5, idUsuario);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registroReporteEspecifico: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error registroReporteEspecifico: " + e);
            }
        }
        return false;
    }

    public boolean consultaReporteEspecifico() {
        try {
            String consulta = "Select * from reporteEspecifico re inner join documento on documento.idDocumento = re.idDocumento inner join usuario on re.idUsuario = usuario.idusuario where re.idDocumento = ? order by re.version asc;";

            pst = conn.prepareStatement(consulta);
            pst.setString(1, this.idDocumento);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                datos.clear();//Limpiar el arraylist 
                rs.beforeFirst();//Sirve para no perder el primer registro

                while (rs.next()) {
                    this.idDocumento = rs.getString("re.idDocumento");
                    this.nomDocumento = rs.getString("documento.nomDocumento");
                    this.fecha = rs.getString("re.fechaVersion");
                    this.idUsuario = rs.getString("re.idUsuario");
                    this.version = rs.getString("re.version");

                    datos.add(idDocumento);
                    datos.add(nomDocumento);
                    datos.add(fecha);
                    datos.add(idUsuario);
                    datos.add(rs.getString("usuario.primNombre") + " " + rs.getString("usuario.primApellido"));
                    datos.add(version);
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error consultaReporteEspecifico: " + e);
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
                System.out.println("Error consultaReporteEspecifico: " + e);
            }
        }
        return false;
    }

    //Metodos tabla reporte global
    public boolean registroReporteGlobal() {

        try {
            String consulta = "insert into reporteglobal(idReporteGlobal, idDocumento, descargas, busqueda, fecha, idUsuario) values(?,?,?,?,?,?);";

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, 0);
            pst.setString(2, idDocumento);
            pst.setString(3, cantDescargas);
            pst.setString(4, cantBusquedas);
            pst.setString(5, fecha);
            pst.setString(6, idUsuario);

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error registroReporteGlobal: " + e);
        } finally {
            try {
                if (conn != null) {
                    // conn.close();
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

    public boolean consultaReporteGlobal() {
        try {
            String consulta = "Select * from reporteGlobal where idDocumento = ?";

            pst = conn.prepareStatement(consulta);
            pst.setString(1, this.idDocumento);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {

                this.cantBusquedas = rs.getString("busqueda");
                this.cantDescargas = rs.getString("descargas");

                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error consultaReporteGlobal: " + e);
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
                System.out.println("Error consultaReporteGlobal: " + e);
            }
        }
        return false;
    }

    public boolean actualizarReporteGlobalBudquedas() {
        try {
            String actualizacion = "update reporteGlobal set busqueda = ? where idDocumento = ?;";
            pst = conn.prepareStatement(actualizacion);

            pst.setString(1, this.cantBusquedas);
            pst.setString(2, this.idDocumento);

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error actualizarReporteGlobalBudquedas: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error actualizarReporteGlobalBudquedas: " + e);
            }
        }
        return false;
    }

    public boolean actualizarReporteGlobalDescargas() {
        try {
            String actualizacion = "update reporteGlobal set descargas = ? where idDocumento = ?;";
            pst = conn.prepareStatement(actualizacion);

            pst.setString(1, this.cantDescargas);
            pst.setString(2, this.idDocumento);

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error actualizarReporteGlobalDescargas: " + e);
        } finally {
            try {
                if (conn != null) {
                    //conn.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error actualizarReporteGlobalDescargas: " + e);
            }
        }
        return false;
    }
}
