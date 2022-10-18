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

        Connection connection = (Connection) sCtx.getAttribute("myConnection");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select usertype from login where username='" + user + "' and " + "password='" + password + "' ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }







        RequestDispatcher read = req.getRequestDispatcher("dash");
        read.forward(req, res);


    }

    public User login(String username, String password ){
        User user = null;

        try {
            Connection connection = (Connection) sCtx.getAttribute("myConnection");
            Statement statement = connection.createStatement();
            System.out.println("select * from login where username='" + username + "' and " + "password='" + password + "' ");
            ResultSet resultSet = statement.executeQuery("select * from login where username='" + username + "' and " + "password='" + password + "' ");
        while (resultSet.next()){
            user = new User();
           // user.setId((long) resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));


            System.out.println("username:==" + username);
            System.out.println("password:==" + password);

            System.out.println("hashed password::" + DigestUtils.md5Hex(password));
        }
        } catch (SQLException e) {
            System.out.println("error "+ e.getMessage());
        }
        return user;
    }
}
