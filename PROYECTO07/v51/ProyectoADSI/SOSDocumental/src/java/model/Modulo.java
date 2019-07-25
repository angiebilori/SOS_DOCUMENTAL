package model;

/**
 *
 * @author Einer
 */
public class Modulo {
    private String idModulo;
    private String codigoModulo;
    private String nomModulo;
    private String descripcionModulo;
    private String linkAccesoModulo;
    private String estadoModulo;
    private String idClasificacionModulo;

    public Modulo(String idModulo, String codigoModulo, String nomModulo, String descripcionModulo, String linkAccesoModulo, String estadoModulo, String idClasificacionModulo) {
        this.idModulo = idModulo;
        this.codigoModulo = codigoModulo;
        this.nomModulo = nomModulo;
        this.descripcionModulo = descripcionModulo;
        this.linkAccesoModulo = linkAccesoModulo;
        this.estadoModulo = estadoModulo;
        this.idClasificacionModulo = idClasificacionModulo;
    }

    public Modulo() {
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public String getCodigoModulo() {
        return codigoModulo;
    }

    public void setCodigoModulo(String codigoModulo) {
        this.codigoModulo = codigoModulo;
    }

    public String getNomModulo() {
        return nomModulo;
    }

    public void setNomModulo(String nomModulo) {
        this.nomModulo = nomModulo;
    }
    
    public String getDescripcionModulo() {
        return descripcionModulo;
    }

    public void setDescripcionModulo(String descripcionModulo) {
        this.descripcionModulo = descripcionModulo;
    }

    public String getLinkAccesoModulo() {
        return linkAccesoModulo;
    }

    public void setLinkAccesoModulo(String linkAccesoModulo) {
        this.linkAccesoModulo = linkAccesoModulo;
    }

    public String getEstadoModulo() {
        return estadoModulo;
    }

    public void setEstadoModulo(String estadoModulo) {
        this.estadoModulo = estadoModulo;
    }

    public String getIdClasificacionModulo() {
        return idClasificacionModulo;
    }

    public void setIdClasificacionModulo(String idClasificacionModulo) {
        this.idClasificacionModulo = idClasificacionModulo;
    }
    
    
    
}
