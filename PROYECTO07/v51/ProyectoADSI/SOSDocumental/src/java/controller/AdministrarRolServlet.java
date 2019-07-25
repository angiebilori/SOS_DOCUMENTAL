package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Modulo;
import model.Rol;
import model.Perfil;
import model.RolPerfil;
import model.dao.DAOModulo;
import model.dao.DAORol;
import model.dao.DAOPerfil;
import model.dao.DAORolPerfil;

/**
 *
 * @author Einer
 */
public class AdministrarRolServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Instanciar clases
    Rol rol = new Rol();
    DAORol dRol = new DAORol();

    Modulo mod = new Modulo();
    DAOModulo dMod = new DAOModulo();

    Perfil per = new Perfil();
    DAOPerfil dPer = new DAOPerfil();

    RolPerfil rolPer = new RolPerfil();
    DAORolPerfil dRolPer = new DAORolPerfil();

    //Variables
    private String idNomRol;
    private String idRol;
    private String codigoRol;
    private String nomRol;
    private String descripcionRol;
    private String estadoRol;
    private String idPerfil[];
    private String accion;
    private String error;
    private String ok;
    private String ultimoId;
    private String linkRedireccionar;

    private ArrayList<Rol> listar;
    private ArrayList<Modulo> listarModulos;
    private final ArrayList<ArrayList<Perfil>> listarPerMod = new ArrayList();
    private ArrayList<Perfil> listarPerRol;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        
        //Obtienes los datos del jsp
        idNomRol = request.getParameter("idNomRol");
        idRol = request.getParameter("idRol");
        codigoRol = request.getParameter("codigoRol");
        nomRol = request.getParameter("nomRol");
        descripcionRol = request.getParameter("desRol");
        estadoRol = request.getParameter("estadoRol");
        idPerfil = request.getParameterValues("idPerfil");
        accion = request.getParameter("accion");
        linkRedireccionar = request.getParameter("modulo");//El link del modulo se encuentra en la sesion

        //asignar los valores
        rol.setIdRol(idRol);
        rol.setCodigoRol(codigoRol);
        rol.setNomRol(nomRol);
        rol.setDescripcionRol(descripcionRol);
        rol.setEstadoRol(estadoRol);
        
        try {
            switch (accion) {
                case "Nuevo Rol":
                    if (nuevoRol()) {
                        request.setAttribute("registrarRol", ultimoId);
                    } else {
                        request.setAttribute("errorRol", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Agregar":
                    if (registrarRol()) {
                        request.setAttribute("okRol", ok);
                    } else {
                        request.setAttribute("errorRol", error);
                    }
                    redireccionar(request, response);
                    break;

                //Este codigo sirve para consultar el rol por id o todo
                case "Consultar":
                    if (consultarRol()) {
                        request.setAttribute("listarRol", listar);
                    } else {
                        request.setAttribute("errorRol", error);
                    }
                    redireccionar(request, response);
                    break;

                //Este codigo sirve para listar el rol a modificar
                case "Modificar":
                    if (datosModificar()) {
                        request.setAttribute("modificarRol", listar);
                    } else {
                        request.setAttribute("errorRol", error);
                    }
                    redireccionar(request, response);
                    break;

                //Guardar el rol modificado
                case "Guardar":
                    if (guardarModificacion()) {
                        request.setAttribute("okRol", ok);
                    } else {
                        request.setAttribute("errorRol", error);
                    }
                    redireccionar(request, response);
                    break;

                //Consulta los permisos del rol
                case "Permisos":
                    if (permisosRol()) {
                        request.setAttribute("nomRol", nomRol);
                        request.setAttribute("idRol", idRol);
                        request.setAttribute("listarPerModRol", listarPerMod);
                        request.setAttribute("listarModulosRol", listarModulos);
                        request.setAttribute("listarPerRolRol", listarPerRol);
                    } else {
                        request.setAttribute("errorRol", error);
                    }
                    redireccionar(request, response);
                    break;

                //Cambia los permisos del rol
                case "Cambiar":
                    //idRol = request.getParameter("idRolP");
                    if (cambiarPermisos()) {
                        request.setAttribute("okRol", ok);
                    } else {
                        request.setAttribute("errorRol", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Cancelar":
                    redireccionar(request, response);
                    break;
            }

        } catch (IOException | ServletException | NullPointerException e) {
            response.sendRedirect("MenuUsuario?accion=cerrarSesion"); //Si es verdadero nos envia a el menu jsp
        }
    }

    //Metodos para registrar consultar etc
    protected boolean nuevoRol() {
        ultimoId = dRol.listarRolUltimoId();
        if (ultimoId != null) {
            return true;
        } else {
            error = "No se logró onbener el ultimo id";
        }
        return false;
    }

    protected boolean registrarRol() {
        if (dRol.registrarRol(rol)) {
            ok = "El rol se registro";
            return true;
        } else {
            error = "El rol no se registro";
        }
        return false;
    }

    protected boolean consultarRol() {
        rol.setIdRol(idNomRol);
        rol.setNomRol(idNomRol);
        listar = dRol.listarRolIdNom(rol);

        if (listar != null) {
            return true;
        } else {
            error = "No se encontraron resultados";
        }
        return false;
    }

    protected boolean datosModificar() {
        listar = dRol.listarRolId(rol);
        if (listar != null) {
            return true;
        } else {
            error = "No se obtuvieron los datos a modificar";
        }
        return false;
    }

    protected boolean guardarModificacion() {
        if (dRol.actualizarRol(rol)) {
            ok = "El rol se modifico";
            return true;
        } else {
            error = "El rol no se modifico";
            return false;
        }
    }

    protected boolean permisosRol() {
        listarPerMod.clear();
        if (idRol != null) {
            listarModulos = dMod.listarModulo();
            for (int i = 0; i < listarModulos.size(); i++) {
                per.setIdModulo((String) listarModulos.get(i).getIdModulo());
                listarPerMod.add(dPer.listarPerfilIdMod(per));
            }
            per.setIdRol(idRol);
            listarPerRol = dPer.listarPerfilIdRol(per);
            nomRol = (String) dRol.listarRolId(rol).get(0).getNomRol();
            return true;
        } else {
            error = "No se cargaron los permisos";
        }
        return false;
    }

    protected boolean cambiarPermisos() {
        rolPer.setIdRol(idRol);
        dRolPer.eliminarRolPerfil(rolPer);
        for (int i = 0; i < idPerfil.length; i++) {
            rolPer.setIdPerfil(idPerfil[i]);
            if (!dRolPer.agregarRolPerfil(rolPer)) {
                error = "Error al agregar los nuevos permisos";
                return false;
            }
        }
        ok = "Permisos cambiados";
        return true;
    }

    protected boolean redireccionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(linkRedireccionar).forward(request, response);
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
