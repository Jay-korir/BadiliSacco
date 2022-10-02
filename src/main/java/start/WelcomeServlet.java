package start;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String show = req.getParameter("action");


         PrintWriter pw = resp.getWriter();
         pw.print(this.Welcome());
       // resp.sendRedirect("http://localhost:8080/BadiliSacco/");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter write = resp.getWriter();
        String name = req.getParameter("UserName");
        write.print(this.Welcome());
        write.print("welcome to the number one sacco :" + name);
        System.out.println("welcome to BADILI SACCO " + name);

    }

    public String Welcome(){
        return "<!DOCTYPE html>"
                + "<html> "
                + "<style>"
                +" h4 {text-align: center;}"
                + "h6 {text-align: center;}"
                +"</style>"
                + "<body bgcolor=\"Lightskyblue\" style=\"margin: auto; width: 220px;\">"
                +"<h4>WELCOME TO BADILI SACCO </h4>"
                +"<h6> Jipange uzeeni </h6>"
                + "<form action=\"./welcome\" method=\"post\">"
                + "<h1> BADILI SACCO  </h1>"
                  +"</form>"
                + "</body>"
                + "</html>";
    }
}
