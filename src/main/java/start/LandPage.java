package start;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LandPage  extends HttpServlet {
    ServletConfig config;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(this.LandPage());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }
    public String LandPage(){
        return "<!DOCTYPE html>"
                + "<html> "
                + "<style>"
                +" h4 {text-align: center;}"
                + "h6 {text-align: center;}"
                +"</style>"
                + "<body bgcolor=\"Lightskyblue\" style=\"margin: auto; width: 220px;\">"
                +"<h4>WELCOME TO BADILI SACCO </h4>"
                +"<h6> Jipange uzeeni </h6>"
                + "Click to Register <a href='./register'>Register</a><br/>"
                + "Click  to Login <a href='./login'>Login</a><br/>"
                + "</body>"
                + "</html>";
    }
}
