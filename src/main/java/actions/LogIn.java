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
          session.setAttribute("username",user.getUsername());
        session.setAttribute("loggedInTime"," logged in time :" + new Date());




        RequestDispatcher read = req.getRequestDispatcher("dash");
        read.forward(req, res);


    }
public  String loginTemplate(String actionError){
        return  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +

                "  <head>\n" +
                "    <!-- Required meta tags -->\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <title>Badili Sacco" +
                "</title>\n" +
                "    <!-- plugins:css -->\n" +
                "    <link rel=\"stylesheet\" href=\"./template/assets/vendors/mdi/css/materialdesignicons.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"./template/assets/vendors/css/vendor.bundle.base.css\">\n" +
                "    <!-- endinject -->\n" +
                "    <!-- Plugin css for this page -->\n" +
                "    <!-- End plugin css for this page -->\n" +
                "    <!-- inject:css -->\n" +
                "    <!-- endinject -->\n" +
                "    <!-- Layout styles -->\n" +
                "    <link rel=\"stylesheet\" href=\"./template/assets/css/style.css\">\n" +
                "    <!-- End layout styles -->\n" +
                "    <link rel=\"shortcut icon\" href=\"./template/assets/images/favicon.png\" />\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"container-scroller\">\n" +
                "      <div class=\"container-fluid page-body-wrapper full-page-wrapper\">\n" +
                "        <div class=\"row w-100 m-0\">\n" +
                "          <div class=\"content-wrapper full-page-wrapper d-flex align-items-center auth login-bg\">\n" +
                "            <div class=\"card col-lg-4 mx-auto\">\n" +
                "              <div class=\"card-body px-5 py-5\">\n" +
                "                <h3 class=\"card-title text-left mb-3\">Login</h3>\n" +
                "                <form   action=\"./login\" method=\"post\">\n" +
                "                  <div class=\"form-group\">\n" +
                "                    <label>Username or email *</label>\n" +
                "                    <input type=\"text\" class=\"form-control p_input\">\n" +
                "                  </div>\n" +
                "                  <div class=\"form-group\">\n" +
                "                    <label>Password *</label>\n" +
                "                    <input type=\"text\" class=\"form-control p_input\">\n" +
                "                  </div>\n" +
                "                  <div class=\"form-group d-flex align-items-center justify-content-between\">\n" +
                "                    <div class=\"form-check\">\n" +
                "                      <label class=\"form-check-label\">\n" +
                "                        <input type=\"checkbox\" class=\"form-check-input\"> Remember me </label>\n" +
                "                    </div>\n" +
                "                    <a href=\"#\" class=\"forgot-pass\">Forgot password</a>\n" +
                "                  </div>\n" +
                "                  <div class=\"text-center\">\n" +
                "                    <button type=\"submit\" class=\"btn btn-primary btn-block enter-btn\">Login</button>\n" +
                "                  </div>\n" +
                "\n" +
                "                  <p class=\"sign-up\">Don't have an Account?<a href='./register'>Register</a></p>\n" +
                "                </form>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "          <!-- content-wrapper ends -->\n" +
                "        </div>\n" +
                "        <!-- row ends -->\n" +
                "      </div>\n" +
                "      <!-- page-body-wrapper ends -->\n" +
                "    </div>\n" +
                "    <!-- container-scroller -->\n" +
                "    <!-- plugins:js -->\n" +
                "    <script src=\"../assets/vendors/js/vendor.bundle.base.js\"></script>\n" +
                "    <!-- endinject -->\n" +
                "    <!-- Plugin js for this page -->\n" +
                "    <!-- End plugin js for this page -->\n" +
                "    <!-- inject:js -->\n" +
                "    <script src=\"../../assets/js/off-canvas.js\"></script>\n" +
                "    <script src=\"../../assets/js/hoverable-collapse.js\"></script>\n" +
                "    <script src=\"../../assets/js/misc.js\"></script>\n" +
                "    <script src=\"../../assets/js/settings.js\"></script>\n" +
                "    <script src=\"../../assets/js/todolist.js\"></script>\n" +
                "    <!-- endinject -->\n" +
                "  </body>\n" +
                "</html>";
}

    public String loginHtml(String actionError){

        return
                "<!DOCTYPE html>"
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
/*
 public void insertLogin(Connection connection, Members members){

        if ( members == null  || StringUtils.isBlank(members.getUserName())||StringUtils.isBlank(members.getPassword()))
            return;

        try {

            Statement statement = connection.createStatement();

            statement.executeUpdate("insert into login(username, password) " +
                    "values('" + members.getUserName().trim() + "','" + members.getPassword() + "')");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 */