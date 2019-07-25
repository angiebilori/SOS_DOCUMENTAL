package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Modulo;
import model.Perfil;
import model.Rol;
import model.Usuario;

/**
 *
 * @author Einer
 */
public class MenuUsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession objSesion = request.getSession();

        ArrayList<Usuario> datosUsu = (ArrayList) objSesion.getAttribute("datosUsu");
        ArrayList<ArrayList<Modulo>> datosMod = (ArrayList) objSesion.getAttribute("datosMod");
        ArrayList<Perfil> datosPer = (ArrayList) objSesion.getAttribute("datosPer");
        ArrayList<Rol> datosRol = (ArrayList) objSesion.getAttribute("datosRol");

        String accion = request.getParameter("accion");//Lo que se va a hacer cuando se clikea un boton
        String idCla = request.getParameter("idCla"); //id Clasificacion del modulo
        String idMod = request.getParameter("idMod"); // id del mosulo

        //Url de los menus
        String urlOpcionesMenu = "OpcionesMenu.jsp";
        String urlAutenticarUsu = "AutenticarUsuario.jsp";

        String urlMod = "";

        //Borrar los datos de los demas modulos
        objSesion.removeAttribute("modulo");
        objSesion.removeAttribute("permisos");

        //objSesion.removeAttribute("datosClaMod");
        //objSesion.removeAttribute("datosUsu");
        //objSesion.removeAttribute("datosRol");
        //objSesion.removeAttribute("datosPer");
        //objSesion.removeAttribute("datosMod");
        //objSesion.removeAttribute("contrasena");
        //objSesion.removeAttribute("cambiar");
        try {
            switch (accion) {
                //Consulta los modulos de la clasificación seleccionada
                case "consultar":
                    request.setAttribute("modulo", urlOpcionesMenu);
                    response.sendRedirect(urlOpcionesMenu + "?idCla=" + idCla);
                    break;

                case "cargarModulo":

                    for (int i = 0; i < datosMod.size(); i++) {
                        for (int j = 0; j < datosMod.get(i).size(); j++) {
                            if (datosMod.get(i).get(0).getIdModulo().equals(idMod)) {
                                urlMod = datosMod.get(i).get(0).getLinkAccesoModulo();
                            }
                        }
                    }

                    objSesion.setAttribute("modulo", urlMod);
                    objSesion.setAttribute("moduloSelect", urlMod);
                    objSesion.setAttribute("permisos", permisos(datosUsu, datosPer, datosMod, datosRol, idMod));

                    response.sendRedirect(urlMod);
                    break;

                case "cerrarSesion":
                    String codigoRestaurar = (String) objSesion.getAttribute("codigoRestaurar");
                    objSesion.invalidate();

                    //Para no perder le codigo de restaurar contraseña
                    objSesion = request.getSession();
                    objSesion.setAttribute("codigoRestaurar", codigoRestaurar);
                    response.sendRedirect(urlAutenticarUsu);
                    break;

                default:
                    break;
            }

        } catch (IOException | NullPointerException e) {
            
            response.sendRedirect(urlAutenticarUsu); //Si es verdadero nos envia a el login
        }
    }

    //Metodo donde se validan los permisos para cada modulo
    private ArrayList permisos(ArrayList<Usuario> datosUsu, ArrayList<Perfil> datosPer, ArrayList<ArrayList<Modulo>> datosMod, ArrayList<Rol> datosRol, String idMod) {

        //Rcibe datos del usuario, del los modulos al que puede acceder y los permisos de lo que puede hacer
        ArrayList permisos = new ArrayList();

        String codMod = "MOD-" + idMod;
        String perLis = "PER-LIS-" + idMod;
        String perCre = "PER-CRE-" + idMod;
        String perMod = "PER-MOD-" + idMod;
        String estado = "Activo";

        if ((datosUsu != null && datosMod != null) && (datosPer != null && datosRol != null)) {

            for (int i = 0; i < datosMod.size(); i++) {
                if (((datosMod.get(i).get(0).getCodigoModulo().equals(codMod)) && (datosUsu.get(0).getEstadoUsuario().equals(estado))) && (datosRol.get(0).getEstadoRol().equals(estado))) {
                    codMod = "0";
                    for (int j = 0; j < datosPer.size(); j++) {

                        if ((datosPer.get(j).getCodigoPerfil().equals(perLis)) && (datosPer.get(j).getEstadoPerfil().equals(estado))) {
                            perLis = "0";
                        }
                        if ((datosPer.get(j).getCodigoPerfil().equals(perCre)) && (datosPer.get(j).getEstadoPerfil().equals(estado))) {
                            perCre = "0";
                        }
                        if ((datosPer.get(j).getCodigoPerfil().equals(perMod)) && (datosPer.get(j).getEstadoPerfil().equals(estado))) {
                            perMod = "0";
                        }
                    }
                }
            }
        }
        permisos.add(perLis);
        permisos.add(perCre);
        permisos.add(perMod);
        return permisos;
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
