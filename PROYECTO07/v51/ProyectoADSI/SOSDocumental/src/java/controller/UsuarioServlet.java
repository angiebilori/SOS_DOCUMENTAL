package controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ClasificacionModulo;
import model.Correo;
import model.Modulo;
import model.Perfil;
import model.Rol;
import model.Usuario;
import model.dao.DAOClasificacionModulo;
import model.dao.DAOModulo;
import model.dao.DAOPerfil;
import model.dao.DAORol;
import model.dao.DAOUsuario;

/**
 *
 * @author Einer
 */
public class UsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Estanciar clases
    Usuario usu = new Usuario();
    DAOUsuario dUsu = new DAOUsuario();

    Rol rol = new Rol();
    DAORol dRol = new DAORol();

    Perfil per = new Perfil();
    DAOPerfil dPer = new DAOPerfil();

    Modulo mod = new Modulo();
    DAOModulo dMod = new DAOModulo();

    ClasificacionModulo ClaMod = new ClasificacionModulo();
    DAOClasificacionModulo dClasMod = new DAOClasificacionModulo();

    Correo enviarCorreo = new Correo();

    //Varibles
    private String idNomUsuario;
    private String idUsuario;
    private String primNombre;
    private String segNombre;
    private String primApellido;
    private String segApellido;
    private String correo;
    private String idRol;
    private String contrasena;
    private String estadoUsuario;
    private String idUsuarioSelect;

    private String nuevaContrasena;
    private String verificarContrasena;

    private String accion;
    private String ok;
    private String error;

    private String codigoRestaurar;

    private String linkRedireccionar;
    //String urlAutenticarUsu = "AutenticarUsuario.jsp";
    private final String urlCambiarContrasena = "CambioContrasena.jsp";
    private final String urlMenuUsuario = "MenuUsuario.jsp";
    private final String urlRestaurarContrasena = "RestaurarContrasena.jsp";
    //private String urlAdministrarUsuario = "AdministrarUsuario.jsp";

    //Datos para la sesion
    public ArrayList<Usuario> datosUsu = new ArrayList();
    private ArrayList<Rol> datosRol;
    private ArrayList<Perfil> datosPer;
    private final ArrayList<ArrayList<Modulo>> datosMod = new ArrayList();

    private ArrayList<ClasificacionModulo> datosClaMod;
    private ArrayList<ArrayList> listar;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Throwable {

        response.setContentType("text/html;charset=UTF-8");

        //Objetos almacenados en la sesion
        HttpSession objSesion = request.getSession(true);

        //Variables
        idNomUsuario = request.getParameter("idNomUsuario");
        idUsuario = request.getParameter("idUsuario");
        primNombre = request.getParameter("primNombre");
        segNombre = request.getParameter("segNombre");
        primApellido = request.getParameter("primApellido");
        segApellido = request.getParameter("segApellido");
        correo = request.getParameter("correo");
        idRol = request.getParameter("idRol");
        contrasena = request.getParameter("contrasena");
        estadoUsuario = request.getParameter("estadoUsuario");
        idUsuarioSelect = request.getParameter("idUsuarioSelect");

        nuevaContrasena = request.getParameter("nuevaContrasena");
        verificarContrasena = request.getParameter("verificarContrasena");

        accion = request.getParameter("accion");

        String autenticar = "AutenticarUsuario.jsp";
        linkRedireccionar = autenticar;

        //Asignar datos a las clases
        usu.setIdUsuario(idUsuario);
        usu.setPrimNombre(primNombre);
        usu.setSegNombre(segNombre);
        usu.setPrimApellido(primApellido);
        usu.setSegApellido(segApellido);
        usu.setCorreo(correo);
        usu.setIdRol(idRol);
        usu.setContrasena(contrasena);
        usu.setEstadoUsuario(estadoUsuario);

        //Variables de modulos
        //Proceso
        try {

            switch (accion) {

                case "recargarUsuario":
                    datosUsu = (ArrayList) objSesion.getAttribute("datosUsu");
                    if ((cargarDatosRol() && cargarDatosPer()) && (cargarDatosMod() && cargarClasificacionMod())) {
                        objSesion.setAttribute("datosUsu", datosUsu);
                        objSesion.setAttribute("datosRol", datosRol);
                        objSesion.setAttribute("datosPer", datosPer);
                        objSesion.setAttribute("datosMod", datosMod);
                        objSesion.setAttribute("datosClaMod", datosClaMod);
                    }
                    response.sendRedirect(urlMenuUsuario);
                    break;

                //Este codigo sirve para autenticarse
                case "Iniciar Sesion":
                    if (autenticarUsuario()) {
                        if (tamanoContrasena()) {
                            if ((cargarDatosRol() && cargarDatosPer()) && (cargarDatosMod() && cargarClasificacionMod())) {
                                objSesion.setAttribute("datosUsu", datosUsu);
                                objSesion.setAttribute("datosRol", datosRol);
                                objSesion.setAttribute("datosPer", datosPer);
                                objSesion.setAttribute("datosMod", datosMod);
                                objSesion.setAttribute("datosClaMod", datosClaMod);
                                response.sendRedirect(urlMenuUsuario);
                                break;

                            } else {
                                request.setAttribute("errorUsu", error);
                            }
                        } else {
                            request.setAttribute("idUsuario", idUsuario);
                            request.setAttribute("contrasena", contrasena);
                            linkRedireccionar = urlCambiarContrasena;
                        }
                    } else {
                        request.setAttribute("errorUsu", error);
                    }
                    redireccionar(request, response);
                    break;

                //Seguir ARREGlando
                case "Nuevo Usuario":
                    linkRedireccionar = (String) objSesion.getAttribute("modulo");
                    if (listarRol()) {
                        request.setAttribute("registrarUsu", "1");
                        request.setAttribute("datosRolUsu", datosRol);
                    } else {
                        request.setAttribute("errorUsu", error);
                    }
                    redireccionar(request, response);
                    break;

                // codigo sirve para registrar un nuevo usuario
                case "Registrar":
                    linkRedireccionar = (String) objSesion.getAttribute("modulo");
                    if (!datosUsuId() && !validarCorreo()) {
                        request.setAttribute("errorUsu", error);
                    } else {
                        contrasena = generarContrasena();
                        usu.setContrasena(contrasena);
                        if (dUsu.registrarUsuario(usu) && correoRegistro()) {
                            error = "";
                        } else {
                        }
                    }
                    ok = "";
                    request.setAttribute("error", error);
                    request.setAttribute("ok", ok);
                    redireccionar(request, response);

                    break;

                case "Consultar":
                    linkRedireccionar = (String) objSesion.getAttribute("modulo");
                    usu.setIdUsuario(idNomUsuario);
                    per.setPrimNombre(idNomUsuario);

                    if (datosUsuIdNom()) {
                        request.setAttribute("listarUsu", datosUsu);
                    } else {
                        request.setAttribute("errorUsu", error);
                    }
                    redireccionar(request, response);
                    break;

                //Codigo para cargar los datos del usuario a modificar
                case "Modificar":
                    usu.setIdUsuario(idUsuarioSelect);
                    linkRedireccionar = (String) objSesion.getAttribute("modulo");
                    if (listarRol() && datosUsuId()) {
                        request.setAttribute("modificarUsu", datosUsu);
                        request.setAttribute("datosRolUsu", datosRol);
                    } else {
                        request.setAttribute("errorUsu", error);
                    }
                    redireccionar(request, response);
                    break;

                //Codigo para guardar los datos del usuario a modificar
                case "Guardar":
                    linkRedireccionar = (String) objSesion.getAttribute("modulo");

                    dUsu.actualizarUsuario(usu, idUsuarioSelect);
                    redireccionar(request, response);
                    break;

                //Código para cambiar la contraseña 
                case "Cambiar":
                    contrasena = "Intentar nuevamente";
                    if (nuevaContrasena.equals(verificarContrasena) && verificarContrasena.length() > 9) {
                        if (validarContrasena(nuevaContrasena)) {
                            if (autenticarUsuario() && modificarContrasena()) {
                                enviarCorreo();
                                contrasena = "";

                            }
                        }
                    } else {
                        error = "Las contraseñas no coinciden y/o es menor a 9 digitos";
                    }
                    linkRedireccionar = urlCambiarContrasena;
                    request.setAttribute("contrasena", contrasena);
                    request.setAttribute("idUsuario", idUsuario);
                    request.setAttribute("errorUsu", error);
                    request.setAttribute("okUsu", ok);
                    redireccionar(request, response);
                    break;

                //Para restaurar la contraseña
                case "solicitarRestaurar":
                    request.setAttribute("restaurar", "1");
                    linkRedireccionar = urlRestaurarContrasena;
                    redireccionar(request, response);
                    break;

                case "Solicitar":
                    if (objSesion.getAttribute("codigoRestaurar") == null) {
                        request.setAttribute("restaurar", "1");
                        if (datosUsuId()) {
                            if (datosUsu.get(0).getCorreo().equals(correo)) {
                                if (datosUsu.get(0).getEstadoUsuario().equals("Activo")) {
                                    codigoRestaurar = generarContrasena();
                                    if (enviarCorreoCodigo()) {
                                        objSesion.setAttribute("codigoRestaurar", codigoRestaurar);
                                        request.setAttribute("restaurar", "2");
                                        request.setAttribute("idUsuario", idUsuario);
                                        request.setAttribute("correo", correo);
                                        error = "";
                                    }
                                } else {
                                    error = "El usuario se encuentra inactivo";
                                }
                            } else {
                                error = "Usuario y/o correo erroneo";

                            }
                        }
                    } else {
                        request.setAttribute("restaurar", "2");
                        request.setAttribute("idUsuario", idUsuario);
                        request.setAttribute("correo", correo);
                        error = "";
                        ok = "Ya se la ha enviado un código, por favor revisar el correo que registró";
                        System.out.println(codigoRestaurar);
                    }
                    request.setAttribute("errorUsu", error);
                    request.setAttribute("okUsu", ok);

                    linkRedireccionar = urlRestaurarContrasena;
                    redireccionar(request, response);
                    break;

                case "Validar":
                    if (request.getParameter("codigoVerificacion").equals(objSesion.getAttribute("codigoRestaurar"))) {
                        request.setAttribute("restaurar", "3");
                        ok = "Ingresa nueva contraseña";
                        error = "";
                    } else {
                        request.setAttribute("restaurar", "2");
                        ok = "";
                        error = "El código ingresado no coincide con el enviado";
                    }
                    request.setAttribute("idUsuario", idUsuario);
                    request.setAttribute("correo", correo);
                    request.setAttribute("errorUsu", error);
                    request.setAttribute("okUsu", ok);

                    linkRedireccionar = urlRestaurarContrasena;
                    redireccionar(request, response);
                    break;

                case "Restaurar":
                    linkRedireccionar = urlRestaurarContrasena;
                    if (nuevaContrasena.equals(verificarContrasena) && verificarContrasena.length() > 9) {
                        if (validarContrasena(nuevaContrasena)) {
                            if (modificarContrasena() && datosUsuId()) {
                                objSesion.removeAttribute("codigoRestaurar");
                                System.out.println(objSesion.getAttribute("codigoRestaurar"));
                                enviarCorreo();
                                linkRedireccionar = autenticar;
                            }
                        }
                    } else {
                        error = "Las contraseñas no coinciden y/o es menor a 9 digitos";
                        ok = "";
                    }
                    request.setAttribute("restaurar", "3");
                    request.setAttribute("idUsuario", idUsuario);
                    request.setAttribute("correo", correo);
                    request.setAttribute("errorUsu", error);
                    request.setAttribute("okUsu", ok);
                    redireccionar(request, response);
                    break;

                //Este codigo sirve para inhabilitar el usuario
                case "Inhabilitar":
                    // codigo
                    break;

                case "Cancelar":
                    linkRedireccionar = (String) objSesion.getAttribute("modulo");
                    redireccionar(request, response);
                    break;
            }
        } catch (IOException | ServletException | NullPointerException e) {
            System.out.println("Error Servlet usuario " + e);
           // response.sendRedirect("MenuUsuario?accion=cerrarSesion"); //Si es verdadero nos envia a el menu jsp
        }
    }

    public boolean datosUsuId() {
        datosUsu = dUsu.listarUsuarioId(usu);
        if (datosUsu == null) {
            error = "Usurio no encontrado";
            return false;
        }
        return true;
    }

    public boolean validarCorreo() {

        if (dUsu.validarCorreo(usu)) {
            error = "Usuario y/o Correo ya existe";
            return false;
        }
        return true;
    }

    public boolean listarRol() {
        datosRol = dRol.listarRol();
        if (datosRol == null) {
            error = "No se logro listar los roles";
            return false;
        }
        return true;
    }

    public boolean datosUsuIdNom() {
        datosUsu = dUsu.listarUsuarioIdNom(usu);
        if (datosUsu == null) {
            error = "Usurio no encontrado";
            return false;
        }
        return true;
    }

    public boolean autenticarUsuario() {
        datosUsu = dUsu.autenticarUsuario(usu);
        if (datosUsu != null) {
            if (!datosUsu.get(0).getEstadoUsuario().equals("Inactivo")) {
                //linkRedireccionar = "";
                return true;
            } else {
                error = "Usuario Inactivo";
                return false;
            }
        } else {
            error = "Usuario y/o contraseña erroneos";
        }
        return false;
    }

    public boolean tamanoContrasena() {
        if (contrasena.length() <= 9) {
            contrasena = "Primer inicio de sesión, debe cambiar la contraseña";
            return false;
        }
        return true;
    }

    public boolean cargarDatosRol() {
        //Datos del rol
        rol.setIdRol(datosUsu.get(0).getIdRol()); //Seleccionamos el id del rol
        datosRol = dRol.listarRolId(rol);//Listamos los datos del rol
        if (datosRol == null) {
            error = "No se logró cargar los roles";
            return false;
        }
        return true;
    }

    public boolean cargarDatosPer() {
        //Listar perfiles
        per.setIdRol(datosRol.get(0).getIdRol());
        datosPer = dPer.listarPerfilIdRol(per);
        if (datosPer == null) {
            error = "No se logró cargar los perfiles";
            return false;
        }
        return true;
    }

    public boolean cargarDatosMod() throws SQLException {
        datosMod.clear();
        mod.setIdModulo("");
        for (int i = 0; i < datosPer.size(); i++) {
            //Si ya encuentra un modulo con el mismo id no hace nada en los perfiles
            String codPer = datosPer.get(i).getCodigoPerfil();
            //startsWith sirve para hacer un LIKE 'PER-LIS-0%'
            if (codPer.startsWith("PER-LIS-0") && datosPer.get(i).getEstadoPerfil().equals("Activo")) {
                mod.setIdModulo(datosPer.get(i).getIdModulo());
                datosMod.add(dMod.listarModuloId(mod));
            }
        }
        dMod.getConexion().close();
        if (datosMod == null) {
            error = "No se logro cargar los modulos";
            return false;
        }
        return true;
    }

    public boolean cargarClasificacionMod() {
        datosClaMod = dClasMod.listarClasificacionModulo();
        if (datosClaMod == null) {
            error = "No se logró cargar el menu";
            return false;
        }
        return true;
    }

    public boolean modificarContrasena() {
        usu.setContrasena(nuevaContrasena);

        if (dUsu.modificarContrasena(usu)) {
            return true;
        } else {
            error = "No se logró realizar el cambio de contraseña";
            contrasena = "Intentar nuevamente";
        }

        return false;
    }

    public boolean enviarCorreo() throws UnknownHostException, Throwable {
        String asuntoMensaje = "Notificación Cambio de Contraseña ";
        String cuerpoMensaje = ",\n\nLe informamos que se realizó el cambio de contraseña en: S.O.S Documental.\n";
        String nombreUsuario = datosUsu.get(0).getPrimNombre() + " " + datosUsu.get(0).getPrimApellido();

        if (enviarCorreo.enviarCorreo(datosUsu.get(0).getCorreo(), asuntoMensaje, cuerpoMensaje, nombreUsuario)) {
            ok = "Se cambio la contaseña";
            contrasena = "";
            idUsuario = "";
            error = "";
            return true;
        } else {
            contrasena = "";
            idUsuario = "";
            error = "";
            ok = "Se cambio la contraseña, no se envió información";
        }
        return false;
    }

    public boolean enviarCorreoCodigo() throws UnknownHostException, Throwable {
        String asuntoMensaje = "Notificación restaurar Contraseña ";
        String cuerpoMensaje
                = ",\n\nLe informamos que se solicito la restauracion de la contraseña.\n"
                + "\n\nEl código que debe ingresar es: ";
        String codigo = codigoRestaurar;
        String nombreUsuario = datosUsu.get(0).getPrimNombre() + " " + datosUsu.get(0).getPrimApellido();
        String usuario = datosUsu.get(0).getIdUsuario();
        if (enviarCorreo.enviarCorreo(datosUsu.get(0).getCorreo(), asuntoMensaje, cuerpoMensaje, nombreUsuario, codigo, usuario)) {
            ok = "Se envio el código de confirmación al correo registrado";
            return true;
        } else {
            error = "No se logró enviar el codigo de confirmacion";
        }
        return false;
    }

     public boolean correoRegistro() throws UnknownHostException, Throwable {
        String asuntoMensaje = "Notificación de Registro";
        String nombreUsuario = usu.getPrimNombre() + " " + usu.getPrimApellido();
        String cuerpoMensaje = ",\n \nLe informamos que se realizó el registro en: S.O.S Documental.\n \nUsuario: "
                + usu.getIdUsuario() + "\nContraseña:" + contrasena
                + "\n \nRecuerde que en su primer inicio de sesión se solicitará cambiar su contraseña.";

        if (enviarCorreo.enviarCorreo(usu.getCorreo(), asuntoMensaje, cuerpoMensaje,nombreUsuario)) {
            ok = "Se realizó el registro";
            return true;
        } else {
            error = "Se realizó el registro, NO se envió la información ";
        }
        return false;
    }

    protected void redireccionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(linkRedireccionar).forward(request, response);
    }

    public String generarContrasena() {
        String[] ABC = "ABCDEFGHIJKLMNÑOPQRSTVWXYZ".split("");
        String[] abc = "abcdefghijklmnñopqrstvwxyz".split("");
        String[] numeros = "1234567890".split("");
        String[] caracteres = "!#$%&'()*+,-./:;<=>?@[]^_{|}~ ".split("");

        int aleactorioA;
        int aleactorioa;
        int aleactorioN;
        int aleactorioC;
        String pass = "";

        for (int i = 0; i < 2; i++) {
            aleactorioA = (int) (Math.random() * (ABC.length - 1) + 0);
            aleactorioa = (int) (Math.random() * (abc.length - 1) + 0);
            aleactorioN = (int) (Math.random() * (numeros.length - 1) + 0);
            aleactorioC = (int) (Math.random() * (caracteres.length - 1) + 0);

            pass = pass + ABC[aleactorioA] + abc[aleactorioa] + numeros[aleactorioN] + caracteres[aleactorioC];
        }
        String[] resultado = pass.split("");
        String fin = "";
        for (int i = 0; i < resultado.length; i++) {
            fin = fin + resultado[i];
        }
        return pass;
    }

    //Validar si la contraseña cumple con los requisitos
    public boolean validarContrasena(String contrasena) {
        String letra;
        int may = 0, min = 0, num = 0, carac = 0;
        for (int i = 0; i < contrasena.length(); i++) {

            letra = String.valueOf(contrasena.charAt(i));

            if (letra.matches("[A-Z]")) {
                may++;
            } else if (letra.matches("[a-z]")) {
                min++;
            } else if (letra.matches("[0-9]")) {
                num++;
            } else {
                carac++;
            }
        }

        if ((may > 0 && min > 0) && (num > 0 && carac > 0)) {
            return true;
        } else {
            error = "La contraseña debe contener mayusculas, minusculas, números y caracteres especiales";
        }
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
        try {
            processRequest(request, response);
        } catch (Throwable ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Throwable ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
