package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class cambioContrasena_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");

    HttpSession objsesion = request.getSession(false);

    String usuario = (String) objsesion.getAttribute("usuario");

    String cambioContrasena = (String) objsesion.getAttribute("cambioContrasena");

    String error = (String) objsesion.getAttribute("error");
    String ok = (String) objsesion.getAttribute("ok");

    if (usuario == null) {
        if (cambioContrasena == null) {
            //response.sendRedirect("autenticacion.jsp");
        }
    }


      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link type=\"text/css\" href=\"css/cambioContrasena.css\" rel=\"stylesheet\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, user-scalable=yes, initial-scale=0.8,maximun-scale=1.0, minimum-scale=0.6\">\n");
      out.write("\n");
      out.write("        <title>Cambio Contraseña</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"contenedor\">\n");
      out.write("            <form action=\"cambiarContrasena\" method=\"post\">\n");
      out.write("                <div id=\"titulo\">\n");
      out.write("                    Cambiar contraseña\n");
      out.write("                </div>\n");
      out.write("                <div id=\"error\">\n");
      out.write("                    <label id=\"informacionCC\">");
if (cambioContrasena != null) {
      out.print(cambioContrasena);
}
      out.write("</label> \n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div id=\"subtitulo\">\n");
      out.write("                    <Label for=\"usuario\">Usuario</label><br><br><br>\n");
      out.write("                    <Label for=\"password\">Contraseña</Label><br><br><br>\n");
      out.write("                    <Label for=\"nPassword\">Nueva contraseña</Label><br><br><br>\n");
      out.write("                    <Label for=\"vPassword\"> Verificar nueva contraseña</Label>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"txt\">\n");
      out.write("                    <input id=\"usuario\" name=\"usuario\" type=\"number\" required placeholder=\"Cedula\">\n");
      out.write("                    <input id=\"password\" name=\"password\" type=\"password\" required placeholder=\"Contraseña\">\n");
      out.write("                    <input id=\"nPassword\" type=\"password\" name=\"nuevoPassword\" required placeholder=\"Nueva Contraseña\">\n");
      out.write("                    <input id=\"vPassword\" name=\"verificarPassword\" type=\"password\" required placeholder=\"Repetir Neva Contraseña\">\n");
      out.write("                </div>\n");
      out.write("                <div id=\"error\">\n");
      out.write("                    <label id=\"informacionCCo\">");
if (error != null) {
      out.print(error);
objsesion.removeAttribute("error");//Remueve el usuario
                            }
      out.write("</label> \n");
      out.write("                    <label id=\"informacionCCi\">");
if (ok != null) {
      out.print(ok);
objsesion.removeAttribute("ok");//Remueve el usuario
                            }
      out.write("</label> \n");
      out.write("                </div>\n");
      out.write("                <div id=\"btn\">\n");
      out.write("                    ");
if (ok == null) {
      out.write("\n");
      out.write("                    <input type=\"submit\" id=\"botones\" VALUE=\"Siguiente\" >    \n");
      out.write("                    <input type=\"button\" id=\"botones\" onclick=\"location.href = 'autenticacion.jsp'\" value=\"Cancelar\">\n");
      out.write("                    ");
}
                        if (ok != null) {
      out.write("\n");
      out.write("                    <input type=\"button\" id=\"botones\" onclick=\"location.href = 'autenticacion.jsp'\" value=\"Cerrar\">\n");
      out.write("                    ");
objsesion.removeAttribute("cambioContrasena");
                         }
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
