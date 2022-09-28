package start;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LogIn implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter wr = servletResponse.getWriter();
        wr.print("<html lang=\"en\">");
        wr.print("<head>");
       // wr.print("<meta charset="UTF-8">");
  wr.print("<title>LOGIN HERE</title>");
  wr.print("</head>");
  wr.print("<style>");
  wr.print("h2 {text-align: center;}");
  wr.print("</style>");
  wr.print("<body bgcolor=\"Lightskyblue\">");
  wr.print("<center>");
  wr.print("<form>");
  wr.print("<h2>LOGIN FORM</h2>");
  wr.print("Username :<input type=\"text\" name=\"user\" /><br/><br/>");
  wr.print("Password :<input type=\"password\" name=\"pass\" /><br/><br/>");
  wr.print("<input type=\"submit\" value=\"Login\" />");
  wr.print("<br/><br/> ");
  wr.print("Not yet registered click to register:");
  wr.print("</label>");
  wr.print("<input type=\"button\" value=\"REGISTER\"/>");
  wr.print("</form>");
  wr.print("</center>");
  wr.print("</body>");
  wr.print("</html>");



    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
