package controller;


import model.User;
import org.apache.commons.codec.digest.DigestUtils;


import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.Date;


@WebServlet(urlPatterns = "/login", initParams = {
        @WebInitParam(name = "username",value = "jenelle"),
        @WebInitParam(name = "password",value = "5055")
})
public class LogIn extends HttpServlet{
    ServletContext sCtx = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sCtx = config.getServletContext();

    }

    @Override
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
            PrintWriter wr = servletResponse.getWriter();
            wr.print(this.login(null));


        String password = DigestUtils.md5Hex("password");

        System.out.println(password);

    }

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        PrintWriter wr = res.getWriter();
        String userr = req.getParameter("username");
        String password = req.getParameter("Password");

        System.out.println("=================");
        System.out.println(userr);

        if (userr == null || userr.equalsIgnoreCase("")){
            wr.print(this.login("username  is required<br/>"));
        return;
    }
        if (password == null || password.equalsIgnoreCase("")) {
            wr.print(this.login("password is required"));
            return;
            }

        User user = this.login(userr,  password);
          if (user == null) {
                wr.print(this.login("Invalid username & password combination<br/>"));
               return;
            }


        HttpSession session = req.getSession(true);
          session.setAttribute("username",user.getUsername());
        session.setAttribute("loggedInTime"," logged in time :" + new Date());




        RequestDispatcher read = req.getRequestDispatcher("dash");
        read.forward(req, res);

    }

    public String login(String actionError){

        return "<!DOCTYPE html>"
     + "<html>"
           + "<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
             + "<style>"
                + "body {font-family: Arial, Helvetica, sans-serif;}"
                + "form {border: 3px solid #f1f1f1;}"

                + "input[type=text], input[type=password] {"
                + "width: 100%;"
                + "padding: 12px 20px;"
                + " margin: 8px 0;"
                + "display: inline-block;"
                 +" border: 1px solid #ccc;"
                 + "box-sizing: border-box;"
                     + "}"

            +"button {"
          +  "background-color: #04AA6D;"
           +" color: white;"
           +" padding: 14px 20px;"
           +" margin: 8px 0;"
           +" border: none;"
           +" cursor: pointer;"
           +" width: 100%;"
        +"}"
  +"button:hover {"
            + "opacity: 0.8;"
       +"}"

+".cancelbtn {"
          +  "width: auto;"
            +"padding: 10px 18px;"
           + "background-color: #f44336;"
        +"}"

                +".container {"
                +"padding: 16px;"
                +"}"

                +"span.psw {"
                +"float: right;"
                +"padding-top: 16px;"
                +"}"
                +".span.rgs{"
                +"float:center;"

                +" }"

                +" @media screen and (max-width: 300px) {"
                + "span.psw {"
                + "display: block;"
                +" float: none;"
                +" }"
                + ".cancelbtn {"
                + "width: 100%;"
                + " }"
                +"}"
        +"input:invalid:required {"
            +"background-image: linear-gradient(to right, violet, lightgreen);"
        +"}"
        +"input:valid {"
            +"border: 2px solid black;"
        +"}"
                +"</style>"
                +"</head>"
                +"<body bgcolor=\"Lightskyblue\" style=\"margin: auto; width: 220px;\">"
              // + "<h1>" + getServletContext().getInitParameter("applicationLabel") + "</h1>"
               // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                +"<h2> BADILI SACCO </h2>"
                +"<h6> Jipange uzeeni </h6>"
                +"<h2 >Login Form</h2>"


                + "<form   action=\"./login\" method=\"post\">"

                // +"<label>Action:</label> <input type=\"text\" name=\"action\" value=\"login\">"
                + " <div class=\"container\">"
                +"<label ><b>Username</b></label>"
                +"<input type=\"text\" name= \"username\" required placeholder=\"Enter Username\">"

                +" <label ><b>Password</b></label>"
                +  "<input type=\"password\" name= \"Password\" required placeholder=\"Enter Password\">"

                + "<button type=\"submit\">Login</button>"
                +"</div>"

                + "<div class=\"container\" style=\"background-color:#f1f1f1\">"

                + " <span class=\"rgs\">Forgot <a href=\"action\">password?</a></span>"
                + "</div>"
                + "<div>"
                +  " Not yet registered click to register: <a href='./register'>Register</a><br/>"
                //+ "Click to Register <a href='./register'>Register</a><br/>"

                +"</div>"
                +"</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span>"
                +"</body>"
                +"</html>";


    }
    public User login(String username, String password){
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
