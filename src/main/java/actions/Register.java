package actions;

import controller.ContributionController;
import controller.MembersController;
import model.Members;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/register")
public class Register extends HttpServlet {
    ServletContext servletCtx = null;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }
   // private  MYSQLSACCO mysqlsacco;
    /* protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // resp.getWriter().print(this.registerTemplate(null));
    resp.sendRedirect("./register.jsp");
    }


    */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter wr = resp.getWriter();


        String first = req.getParameter("FirstName");
        String last = req.getParameter("LastName");
        String user = req.getParameter("UserName");
        String phone = req.getParameter("Phone");
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");
        String conPassword = req.getParameter("ConfirmPassword");

        System.out.println("=================username");
        System.out.println(user);





        String actionError = "";
                    if (first == null || first.equalsIgnoreCase("")){
                        servletCtx.setAttribute("registerError","firstname is required");
                        resp.sendRedirect("./register.jsp");
                   return;
                    }
            if (last == null || last.equalsIgnoreCase("")){
                servletCtx.setAttribute("registerError","lastname is required");
                 resp.sendRedirect("./register.jsp");
               return;
            }
            if (user == null|| user.equalsIgnoreCase("")) {
                servletCtx.setAttribute("registerError", "username is required");
                resp.sendRedirect("./register.jsp");
                return;
            }
            if (email == null || email.equalsIgnoreCase("")) {
                servletCtx.setAttribute("registerError", "email is required");
                resp.sendRedirect("./register.jsp");
                return;
            }
            if (phone == null || phone.equalsIgnoreCase("")) {
                servletCtx.setAttribute("registerError", "phone is required");
                resp.sendRedirect("./register.jsp");
                return;
            }
            if (password == null || password.equalsIgnoreCase("")) {
                servletCtx.setAttribute("registerError", "password is required");
                resp.sendRedirect("./register.jsp");
                return;
            }
            if (conPassword == null || conPassword.equalsIgnoreCase("")) {
                servletCtx.setAttribute("registerError", "confirm password is required");
                resp.sendRedirect("./register.jsp");
                return;
            }
            if ((password != null && conPassword != null) && !password.equals(conPassword)  ) {
                servletCtx.setAttribute("registerError", "password and confirm password dont match");
                resp.sendRedirect("./register.jsp");
                return;
            }


            if((password != null && conPassword != null) && password.equals(conPassword)) {
                  Members members = new Members();
                  members.setFirstName(first);
                  members.setLastName(last);
                  members.setUserName(user);
                  members.setEmail(email);
                  members.setPhone(phone);


                  this.insertMembers(first,last,user,email,phone);


                User userH = new User();
                System.out.println("===============++++++++++++++");
                userH.setUsername(user);

                System.out.println("888888888888888");
                userH.setPassword(DigestUtils.md2Hex(password));
                this.insert(user, password);
                resp.sendRedirect("./login.jsp");
            }
            else
                resp.sendRedirect("./register.jsp");
                //wr.print(this.registerTemplate(actionError));


    }



    public void insert(String username, String password){
        try {
            Connection connection = (Connection) servletCtx.getAttribute("myConnection");
            Statement statement = connection.createStatement();

            statement.executeUpdate("insert into login(username, password,usertype) " +
                    "values('" + username.trim() + "','" + password + "','user')");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertMembers(String firstname, String lastname, String username, String email, String phone){
        try {
            Connection connection = (Connection) servletCtx.getAttribute("myConnection");
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO `members`(`firstname`, `lastname`, `username`, `email`, `phone`) " +
                    "VALUES ('"+ firstname.trim() +"','"+ lastname.trim() +"','"+ username.trim() +"','"+ email.trim() +"','"+ phone.trim() +"')");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
