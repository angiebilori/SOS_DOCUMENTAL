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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Cambio Contraseña</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"Contenedor\">\n");
      out.write("           <script>\n");
      out.write("\n");
      out.write("function processFiles(files) {\n");
      out.write("var file = files[0];\n");
      out.write("\n");
      out.write("var reader = new FileReader();\n");
      out.write("\n");
      out.write("reader.onload = function (e) {\n");
      out.write("// Cuando éste evento se dispara, los datos están ya disponibles.\n");
      out.write("// Se trata de copiarlos a una área <div> en la página.\n");
      out.write("var output = document.getElementById(\"fileOutput\"); \n");
      out.write("fileOutput.style.backgroundImage= \"url('\" + e.target.result + \"')\";\n");
      out.write("};\n");
      out.write("reader.readAsDataURL(file);\n");
      out.write("}\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("                \n");
      out.write("            <div id=\"titulo\">ANEXOS</div>\n");
      out.write("                <center>\n");
      out.write("                <table id=\"cuerpo\"border=\"1px\"> \n");
      out.write("        \n");
      out.write("                <tr>\n");
      out.write("                <td id=\"Anexo1\">\n");
      out.write("<!--                <form id=\"Anexos\" method=\"post\" action=\"processFiles(files)\" enctype=\"multipart/form-data\">-->\n");
      out.write("                <input id=\"files\" type=\"file\"  size=\"50\" onchange=\"processFiles(this.files)\" name=\"files[]\" multiple><br />\n");
      out.write("              <!--  <center><input id=\"enviar\" type=\"submit\" value=\"Enviar\" /></center>\n");
      out.write("                </form>-->\n");
      out.write("                    <output id=\"list\"></output>\n");
      out.write("                    </td>\n");
      out.write("                \n");
      out.write("                <td ><strong>Previsualizacion</strong>\n");
      out.write("                    <embed src=\"\" type=\"application/pdf\" width=\"800\" height=\"600\">\n");
      out.write("                  <!--  <iframe id=\"frame\" name=\"ventana_iframe2\" scrolling=\"yes\" >\n");
      out.write("                    </iframe>-->\n");
      out.write("                    <div id=\"fileOutput\">\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    </div>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("                  <tr>\n");
      out.write("                <td id=\"Anexo1\">\n");
      out.write("<!--                    <input type=\"file\" id=\"files\" name=\"files[]\" multiple />-->\n");
      out.write("                    \n");
      out.write("\n");
      out.write("\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write(" \n");
      out.write("          <script>\n");
      out.write("  function handleFileSelect(evt) {\n");
      out.write("    var files = evt.target.files; // FileList object\n");
      out.write("\n");
      out.write("    // files is a FileList of File objects. List some properties.\n");
      out.write("    var output = [];\n");
      out.write("    for (var i = 0, f; f = files[i]; i++) {\n");
      out.write("      output.push('<li><strong>', escape(f.name), '</strong> (', f.type || 'n/a', ') - ',\n");
      out.write("                  f.size, ' bytes, last modified: ',\n");
      out.write("                  f.lastModifiedDate.toLocaleDateString(), '</li>');\n");
      out.write("    }\n");
      out.write("    document.getElementById('list').innerHTML = '<ul>' + output.join('') + '</ul>';\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  document.getElementById('files').addEventListener('change', handleFileSelect, false);\n");
      out.write("</script>\n");
      out.write("                </table>\n");
      out.write("                </center>\n");
      out.write("                <center>\n");
      out.write("                    <div id=\"boton\">\n");
      out.write("                        <input type=\"submit\" name=\"submit\" id=\"btnBuscar\" value=\"Subir\" >\n");
      out.write("                </div>\n");
      out.write("                </center>\n");
      out.write("                \n");
      out.write("                         \n");
      out.write("                \n");
      out.write("            \n");
      out.write("        \n");
      out.write("        </div>\n");
      out.write("        \n");
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
