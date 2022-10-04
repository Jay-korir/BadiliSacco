package start;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WelcomeServlet extends HttpServlet {
ServletConfig  config = null;
    HttpSession httpSession;
    String activityList;
    public  void  init(ServletConfig config) throws ServletException{
        this.config = config;
    }
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

        httpSession = req.getSession();
        List<String> activity = (List<String>) httpSession.getAttribute("activity");
        System.out.println(activity);
        activityList = "<ul>";
        for (String act : activity) {
            activityList += "<li>" + act + "</li>";
            activityList += "</ul>";
        }
        System.out.println(activityList);
        write.print(activityList);
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
                + "<h1>" + config.getServletContext().getInitParameter("applicationLabel") + "</h1>"
               // + "<h2>   Logged In At: " + httpSession.getAttribute("loggedInTime") + "</h2>"
                + "<span style=\"color:green;font-size: 24px;font-weight:bold\">Logged In</span>"
                + "<br/>"
                + "<a href='./login?action=login'>Logout</a><br/>"
                  +"</form>"
                + "</body>"
                + "</html>";
    }
}

