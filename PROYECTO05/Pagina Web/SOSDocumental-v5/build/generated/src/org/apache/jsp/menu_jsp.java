package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"

    String usuario = (String) objsesion.getAttribute("usuario"); //Obtenermos los datos del objeto
    String rol = (String) objsesion.getAttribute("rol");
    String doc = (String) objsesion.getAttribute("idUsuario");

    //Cuando ingrese al menu se borran los datos utilizados en otros modulos
    objsesion.removeAttribute("datos");
    objsesion.removeAttribute("datosA");
    objsesion.removeAttribute("error");
    objsesion.removeAttribute("ok");
    objsesion.removeAttribute("cambioContrasena");

    if (usuario == null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
        response.sendRedirect("autenticacion.jsp");
    }

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link type=\"text/css\" href=\"css/menu.css\" rel=\"stylesheet\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, user-scalable=yes, initial-scale=1.0,maximun-scale=5.0, minimum-scale=1.0\">\r\n");
      out.write("        <title>Menu</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div class=\"contenedor\">     \r\n");
      out.write("            <form action=\"cerrarSesion\" method=\"post\">\r\n");
      out.write("                <div class=\"logo\">\r\n");
      out.write("                    <a href=\"menu.jsp\"><img src=\"../logo/LOGO.gif\" alt=\"No se puede cargar la imagen\"></a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <ul class=\"menu1\">\r\n");
      out.write("                    <li><a href=\"consultas.jsp\" target=\"ventana_iframe\">Busquedas</a></li>\r\n");
      out.write("                    <li><a href=\"anexos.jsp\" target=\"ventana_iframe\">Documento</a></li>\r\n");
      out.write("                    <li><a href=\"reportes.jsp\" target=\"ventana_iframe\">Reportes</a></li>\r\n");
      out.write("                    <li><a href=\"\">Ayuda</a></li>\r\n");
      out.write("                    <li><a href=\"\">");
out.println(usuario);
      out.write("</a>\r\n");
      out.write("                        <ul class=\"menu2\">\r\n");
      out.write("                            <li id=\"usuario\">CC: ");
out.println(doc);
      out.write(" <br> Rol: ");
out.println(rol);
      out.write("</li>\r\n");
      out.write("                            <li><a href=\"registro.jsp\" target=\"ventana_iframe\">Nuevo Registro</a></li>\r\n");
      out.write("                            <li><a href=\"cambioContrasena.jsp\" target=\"ventana_iframe\">Cambiar Contraseña</a></li>\r\n");
      out.write("                            <li><a href=\"cerrarSesion\">Cerar Sesión</a></li>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("                <div class=\"iframe\">\r\n");
      out.write("                    <iframe id=\"frame\" name=\"ventana_iframe\" scrolling=\"yes\" >\r\n");
      out.write("                    </iframe> \r\n");
      out.write("                </div>\r\n");
      out.write("            </form>   \r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
