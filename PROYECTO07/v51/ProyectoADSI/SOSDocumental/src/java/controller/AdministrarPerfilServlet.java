package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Modulo;
import model.Perfil;
import model.dao.DAOModulo;
import model.dao.DAOPerfil;

/**
 *
 * @author Einer
 */
public class AdministrarPerfilServlet extends HttpServlet {

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
    Modulo mod = new Modulo();
    DAOModulo dMod = new DAOModulo();

    Perfil per = new Perfil();
    DAOPerfil dPer = new DAOPerfil();

    //Variables
    private String idNomPerfil;
    private String idPerfil;
    private String codigoPerfil;
    private String nomPerfil;
    private String descripcionPerfil;
    private String estadoPerfil;
    private String idModulo;
    private String tipoPer;
    private String accion;
    private String linkRedireccionar;
    private String error;
    private String ok;
    private String ultimoId;

    ArrayList<Perfil> listar;
    ArrayList<Perfil> modificar;
    ArrayList<Modulo> listarModulos;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //PrintWriter out = response.getWriter();
        //HttpSession objSesion = request.getSession();
        //Variables
        idNomPerfil = request.getParameter("idNomPerfil");
        idPerfil = request.getParameter("idPerfil");
        codigoPerfil = request.getParameter("codigoPerfil");
        nomPerfil = request.getParameter("nomPerfil");
        descripcionPerfil = request.getParameter("desPerfil");
        estadoPerfil = request.getParameter("estadoPerfil");
        idModulo = request.getParameter("idModulo");

        tipoPer = request.getParameter("tipoPer");
        accion = request.getParameter("accion");

        //asignar los valores
        per.setIdPerfil(idPerfil);
        per.setCodigoPerfil(codigoPerfil);
        per.setNomPerfil(nomPerfil);
        per.setDescripcionPerfil(descripcionPerfil);
        per.setEstadoPerfil(estadoPerfil);
        per.setIdModulo(idModulo);

        //mod.setIdModulo("2"); //asignamos dos del id del modulo de AdministracionPerfil
        //Consultamos el link del modulo 
        //linkModAdPer = (dMod.listarModuloId(mod).get(0).get(4)).toString(); //Almacenamos el link del modulo
        linkRedireccionar = request.getParameter("modulo");//El link del modulo se encuentra en la sesion

        error = "";
        ok = "";
        try {
            switch (accion) {

                case "Nuevo Perfil":
                    if (nuevoPerfil()) {
                        request.setAttribute("registrarPer", ultimoId);
                        request.setAttribute("listarModulosPer", listarModulos);
                    } else {
                        request.setAttribute("errorPer", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Agregar":
                    if (registrarPerfil()) {
                        request.setAttribute("okPer", ok);
                    } else {
                        request.setAttribute("errorPer", error);
                    }
                    redireccionar(request, response);
                    break;

                //Este codigo sirve para consultar el rol por id o todo
                case "Consultar":
                    if (consultarPerfil()) {
                        request.setAttribute("listarPer", listar);
                    } else {
                        request.setAttribute("errorPer", error);
                    }
                    redireccionar(request, response);
                    break;

                //Este codigo sirve para modificar el rol
                case "Modificar":
                    if (datosModificar()) {
                        request.setAttribute("listarModulosPer", listarModulos);
                        request.setAttribute("modificarPer", listar);
                    } else {
                        request.setAttribute("errorPer", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Guardar":
                    if (guardarModificacion()) {
                        request.setAttribute("okPer", ok);
                    } else {
                        request.setAttribute("errorPer", error);
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
    protected boolean nuevoPerfil() {
        ultimoId = dPer.listarPerfilUltimoId();
        listarModulos = dMod.listarModulo();
        if (ultimoId != null && listarModulos != null) {
            return true;
        } else {
            error = "No se logró obtener el ultimo id";
        }
        return false;
    }

    protected boolean registrarPerfil() {
        per.setCodigoPerfil(tipoPer + idModulo);

        if (!dPer.existenciaPerfil(per)) {
            if (dPer.registrarPerfil(per)) {
                ok = "El perfil se registro";
                return true;
            } else {
                error = "El perfil no se registro";
            }
        } else {
            error = "El perfil ya existe";
        }
        return false;
    }

    protected boolean consultarPerfil() {

        per.setIdPerfil(idNomPerfil);
        per.setNomPerfil(idNomPerfil);

        listar = dPer.listarPerfilIdNom(per);
        if (listar != null) {
            return true;
        } else {
            error = "No se encontraron resultados";
        }
        return false;
    }

    protected boolean datosModificar() {
        listar = dPer.listarPerfilIdNom(per);
        listarModulos = dMod.listarModulo();
        if (listar != null && listarModulos != null) {
            return true;
        } else {
            error = "No se obtuvieron los datos a modificar";
        }
        return false;
    }

    protected boolean guardarModificacion() {

        per.setCodigoPerfil(tipoPer + idModulo);

        if (dPer.existenciaPerfil(per)) {

            if (dPer.actualizarPerfilCod(per)) {
                ok = "Ya existe un perfil igual";
                return true;
            }
        } else {
            if (dPer.actualizarPerfilId(per)) {
                ok = "El Perfil se modifico";
                return true;
            }
        }
        error = "El perfil no se modifico";
        return false;
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
