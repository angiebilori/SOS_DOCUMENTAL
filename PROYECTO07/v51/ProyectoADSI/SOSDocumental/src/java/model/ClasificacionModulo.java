package model;

/**
 *
 * @author Einer
 */
public class ClasificacionModulo {

    private String idClasificacionModulo;
    private String nomClasificacionModulo;
    private String descripcionClasificacionModulo;
    private String estadoClasificacionModulo;

    public ClasificacionModulo(String idClasificacionModulo, String nomClasificacionModulo, String descripcionClasificacionModulo, String estadoClasificacionModulo) {
        this.idClasificacionModulo = idClasificacionModulo;
        this.nomClasificacionModulo = nomClasificacionModulo;
        this.descripcionClasificacionModulo = descripcionClasificacionModulo;
        this.estadoClasificacionModulo = estadoClasificacionModulo;
    }

    public ClasificacionModulo() {
    }

    public String getIdClasificacionModulo() {
        return idClasificacionModulo;
    }

    public void setIdClasificacionModulo(String idClasificacionModulo) {
        this.idClasificacionModulo = idClasificacionModulo;
    }

    public String getNomClasificacionModulo() {
        return nomClasificacionModulo;
    }

    public void setNomClasificacionModulo(String nomClasificacionModulo) {
        this.nomClasificacionModulo = nomClasificacionModulo;
    }

    public String getDescripcionClasificacionModulo() {
        return descripcionClasificacionModulo;
    }

    public void setDescripcionClasificacionModulo(String descripcionClasificacionModulo) {
        this.descripcionClasificacionModulo = descripcionClasificacionModulo;
    }

    public String getEstadoClasificacionModulo() {
        return estadoClasificacionModulo;
    }

    public void setEstadoClasificacionModulo(String estadoClasificacionModulo) {
        this.estadoClasificacionModulo = estadoClasificacionModulo;
    }
    
}
