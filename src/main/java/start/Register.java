package start;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Register implements Servlet {

    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
       // servletResponse.setContentType("text/html");
        PrintWriter r = servletResponse.getWriter();
        r.print("<html lang=\"en\">");
        r.print("<head>");
        r.print("<title>");
        r.print("Registration Page");
        r.print("</head>");
        r.print("<body >");
        //bgcolor="Lightskyblue"
        r.print("<br>");
        r.print("<br>");
        r.print("<form>");
        r.print("<label> Firstname </label>");
        r.print("<input type=\"text\" name=\"firstname\" size=\"15\"/> <br> <br>");
        r.print("<label> Lastname: </label>");
        r.print("<input type=\"text\" name=\"lastname\" size=\"15\"/> <br> <br>");
        r.print("<label> UserName: </label>");
        r.print("<input type=\"text\" name=\"middlename\" size=\"15\"/> <br> <br>");
        r.print("<br>");
        r.print("<br>");
        r.print("<label>");
        r.print("Gender :");
        r.print("</label><br>");
        r.print("<input type=\"radio\" name=\"male\"/> Male <br>");
        r.print("<input type=\"radio\" name=\"female\"/> Female <br>");
        r.print("<input type=\"radio\" name=\"other\"/> Other");
        r.print("<br>");
        r.print("<br>");
        r.print("<label>");
        r.print("Phone :");
        r.print("</label>");
        r.print("<input type=\"text\" name=\"country code\"  value=\"+254\" size=\"2\"/>");
        r.print("<input type=\"text\" name=\"phone\" size=\"10\"/> <br> <br>");
        r.print("Address");
        r.print("<br>");
        r.print("<textarea cols=\"80\" rows=\"5\" value=\"address\">");
        r.print("</textarea>");
        r.print("<br> <br>");
        r.print("Email:");
        r.print("<input type=\"email\" id=\"email\" name=\"email\"/> <br>");
        r.print("<br> <br>");
        r.print("Password:");
        r.print("<input type=\"Password\" id=\"pass\" name=\"pass\"> <br>");
        r.print("<br> <br>");
        r.print("Re-type password:");
        r.print("<input type=\"Password\" id=\"repass\" name=\"repass\"> <br> <br>");
        r.print("<input type=\"button\" value=\"Submit\"/><br>");
        r.print("<label>");
        r.print("Already registered click to log in:");
        r.print("</label>");
        r.print("<input type=\"button\" value=\"LOGIN\"/>");
        r.print("</form>");
        r.print("</body>");
        r.print("</html>");


    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
