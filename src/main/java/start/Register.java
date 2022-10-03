package start;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(this.register(null));

    }

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

        String actionError = "";


            System.out.println("FirstName:" + first);
            System.out.println("LastName:" + last);
            System.out.println("UserName:" + user);

            System.out.println("Email:" + email);
            System.out.println("Phone:"+ phone);
            System.out.println("Password:"+ password);
            System.out.println("ConfirmPassword:" +conPassword);
            System.out.println("user successfully registered");


            if (first == null || email.equalsIgnoreCase(""))
                actionError = "please enter a valid first name<br/>";

            if (last == null || email.equalsIgnoreCase(""))
                actionError += "please enter a valid last  name<br/>";

            if (user == null|| email.equalsIgnoreCase(""))
                actionError += "please enter a username<br/>";

            if (email == null || email.equalsIgnoreCase(""))
                actionError = "please enter your email<br/>";

            if (phone == null || email.equalsIgnoreCase(""))
                actionError += "please enter mobile number<br/>";

            if (password == null || email.equalsIgnoreCase(""))
                actionError += "enter valid password<br/>";

            if (conPassword == null || email.equalsIgnoreCase(""))
                actionError = "please confirm password<br/>";

            if ((password != null && conPassword != null) && !password.equals(conPassword)  )
                actionError += "password do not match<br/>";

            if((password != null && conPassword != null) && password.equals(conPassword))
                resp.sendRedirect("/login");
            else
                wr.print(this.register(actionError));


    }

    public String register(String actionError){

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
                +"<h2> BADILI SACCO </h2>"
                +"<h6> Jipange uzeeni </h6>"
                +"<h2 >Login Form</h2>"

                + "<form   action=\"./login\" method=\"post\">"

                +"<label>Action:</label> <input type=\"text\" name=\"action\" value=\"register\">"
                + " <div class=\"container\">"
                +"<label ><b>FirstName</b></label>"
                +"<input type=\"text\" name= \"FirstName\" required placeholder=\"Enter firstname\">"
                +"<label ><b>LastName</b></label>"
                +"<input type=\"text\" name= \"LastName\" required placeholder=\"Enter lastname\">"
                +"<label ><b>Username</b></label>"
                +"<input type=\"text\" name= \"UserName\" required placeholder=\"Enter Username\">"
                +"<label ><b>Email</b></label>"
                +"<input type=\"text\" name= \"Email\" required placeholder=\"Enter email\">"
                +"<label ><b>Phone</b></label>"
                +"<input type=\"text\" name= \"Phone\" required placeholder=\"Enter phone\">"

                +" <label ><b>Password</b></label>"
                +  "<input type=\"password\" name= \"Password\" required placeholder=\"Enter Password\">"
                +" <label ><b>ConfirmPassword</b></label>"
                +  "<input type=\"password\" name= \"ConfirmPassword\" required placeholder=\"Enter Password\">"
                + "<button type=\"submit\">Register</button>"
                +"</div>"

                + "<div class=\"container\" style=\"background-color:#f1f1f1\">"

                + " <span class=\"rgs\">Forgot <a href=\"action\">password?</a></span>"
                + "</div>"
                + "<div>"

                + " Already registered click to login: <a href='./login?action=login'>Login</a><br/>"
                +"</div>"
                +"</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span>"
                +"</body>"
                +"</html>";


    }

}
