package actions;


import model.User;
import org.apache.commons.codec.digest.DigestUtils;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;


@WebServlet(urlPatterns = "/login")
public class LogIn extends HttpServlet{
    ServletContext sCtx = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sCtx = config.getServletContext();

    }


    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        PrintWriter wr = res.getWriter();
        String userr = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("=================");
        System.out.println(userr);
        System.out.println(password);

        if (userr == null || userr.equalsIgnoreCase("")){
       sCtx.setAttribute("loginError","username is required");
        res.sendRedirect("./login.jsp");
            return;
    }
        if (password == null || password.equalsIgnoreCase("")) {
            sCtx.setAttribute("loginError","password is required");
            res.sendRedirect("./login.jsp");
            return;
            }

        User user = this.login(userr,  password);
          if (user == null) {
              sCtx.setAttribute("loginError","username & password is required");
              res.sendRedirect("./login.jsp");
               return;
            }

        HttpSession session = req.getSession(true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userType", user.getUserType());
        sCtx.setAttribute("username", user.getUsername());

        if (user.getUserType().equals("admin"))
            res.sendRedirect("./dashboard.jsp");
        else if (user.getUserType().equals("user"))
            res.sendRedirect("./userDashboard.jsp");

    }

    public User login(String username, String password ){
        User user = null;

        try {
            Connection connection = (Connection) sCtx.getAttribute("myConnection");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from login where username='" + username + "' and " + "password='" + DigestUtils.md2Hex(password) + "' ");
        while (resultSet.next()){
            user = new User();

            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setUserType(resultSet.getString("usertype"));


        }
        } catch (SQLException e) {
            System.out.println("error "+ e.getMessage());
        }
        return user;
    }
}
