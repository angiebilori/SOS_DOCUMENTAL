package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class consultas_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");

    HttpSession objsesion = request.getSession(false);

    String usuario = (String) objsesion.getAttribute("usuario");
    String informacion = (String) objsesion.getAttribute("informacion");

    request.removeAttribute("datos");
    ArrayList<String> datos = (ArrayList<String>) objsesion.getAttribute("datos");

    //if (usuario == null) {
    //  response.sendRedirect("autenticacion.jsp");
    //}


      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <link type=\"text/css\" href=\"css/consultas.css\" rel=\"stylesheet\">\n");
      out.write("        <script type=\"text/javascript\" src=\"jQuery/cargarArchivo.js\"></script>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, user-scalable=yes, initial-scale=1.0,maximun-scale=5.0, minimum-scale=1.0\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Consultas</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"cuerpo\">\n");
      out.write("            <form  action=\"busqueda\" method=\"Post\">\n");
      out.write("                <div id=\"titulo\">Búsquedas</div>\n");
      out.write("                <div id=\"encabezado\">\n");
      out.write("                    <label for=\"opcionbusqueda\">Código o nombre documento</label>    \n");
      out.write("                    <input type=\"text\" name=\"txtIdNom\" required=\"\">\n");
      out.write("\n");
      out.write("                    <label for=\"fecha\">Fecha en que se guardo el documento</label>\n");
      out.write("                    <input type=\"date\" name=\"fecha\" id=\"fecha\">\n");
      out.write("                </div>\n");
      out.write("                <div id=\"btn\">\n");
      out.write("                    <input type=\"submit\" value=\"Buscar\" name=\"btnBuscar\">\n");
      out.write("                    <input type=\"button\" id=\"botones\" onclick=\"location.href = 'menu.jsp'\" value=\"Salir\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                ");
 if (datos != null) {
      out.write("\n");
      out.write("                <div id=\"resultado\">\n");
      out.write("                    <table>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Código Documento</th>                           \n");
      out.write("                            <th>Nombre Documento</th>\n");
      out.write("                            <th>Fecha del documento</th>\n");
      out.write("                            <th>Versión</th>\n");
      out.write("                            <th>Descargar</th>\n");
      out.write("                        </tr>\n");
      out.write("                        ");
for (int i = 0; i < datos.size(); i += 4) {
      out.write("\n");
      out.write("                        \n");
      out.write("                            <td>");
      out.print(datos.get(i));
      out.write("</td>                           \n");
      out.write("                            <td>");
      out.print(datos.get(i + 1));
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(datos.get(i + 2));
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(datos.get(i + 3));
      out.write("</td>\n");
      out.write("                            <td><a href=\"\" value=\"");
      out.print(datos.get(i));
      out.write("\" name=\"idDescargar\">Descargar</a></td>\n");
      out.write("\n");
      out.write("                        \n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                ");
}
                        objsesion.removeAttribute("informacion");
                    }
      out.write("\n");
      out.write("                <div id=\"informacion\">\n");
      out.write("                    <label>");
      out.print(informacion);
      out.write("</label>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
