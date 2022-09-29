package start;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LogIn extends HttpServlet {




    @Override
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        String action = servletRequest.getParameter("action");
        PrintWriter wr = servletResponse.getWriter();

        if (action != null && action.equalsIgnoreCase("register"))
wr.print(this.register(null));
        else if (action != null && action.equalsIgnoreCase("login"))
            wr.print(this.login(null));

    }
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
        PrintWriter wr = res.getWriter();
        String action = req.getParameter("action");
        String first = req.getParameter("FirstName");
        String last = req.getParameter("LastName");
        String user = req.getParameter("UserName");
        String gender = req.getParameter("Gender");
        String phone = req.getParameter("Phone");
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");
        String conPassword = req.getParameter("ConfirmPassword");

        boolean register = action != null && action.equalsIgnoreCase("register");
        boolean login = action != null && action.equalsIgnoreCase("login");

        String actionError = "";

        if (register){
            System.out.println("FirstName:" + first);
            System.out.println("LastName:" + last);
            System.out.println("UserName:" + user);
            System.out.println("Gender:"+ gender);
            System.out.println("Email:" + email);
            System.out.println("Phone:"+ phone);
            System.out.println("Password:"+ password);
            System.out.println("ConfirmPassword:" +conPassword);

            if (first.length() < 2 || email.equalsIgnoreCase(""))
                actionError = "please enter a valid first name<br/>";
            if (last.length() < 2 || email.equalsIgnoreCase(""))
                actionError += "please enter a valid last  name<br/>";
            if (user.length() < 4 || email.equalsIgnoreCase(""))
                actionError += "please enter a username<br/>";
            if (gender == null || email.equalsIgnoreCase(""))
                actionError += "please enter a valid name<br/>";
            if (email.isEmpty() || email.equalsIgnoreCase(""))
                actionError = "please enter your email<br/>";
            if (phone.length() < 10 || email.equalsIgnoreCase(""))
                actionError += "please enter mobile number<br/>";
            if (password.length() < 4 || email.equalsIgnoreCase(""))
                actionError += "enter valid password<br/>";
            if (conPassword.length() < 4 || email.equalsIgnoreCase(""))
                actionError = "please confirm password<br/>";
            if ((password != null && conPassword != null) && !password.equals(conPassword)  )
                actionError += "password do not match<br/>";
       else
           wr.print(this.register(actionError));
        }else if (login){

            System.out.println("UserName: " + user);
            System.out.println("Password: " + password);

            if (user == null ||user.equalsIgnoreCase(""))
                actionError = "username is required <br/>";
            if (password == null || password.equalsIgnoreCase(""))
        actionError += " enter password";

            if (password != null && !password.equals("5055"))
                actionError += "wrong password";
            if (actionError.equals(""))
                System.out.println(user + "successfully logged in ");
        }
        else
            wr.print(this.login(actionError));

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
                +"</style>"
                +"</head>"
                +"<body bgcolor=\"Lightskyblue\" style=\"margin: auto; width: 220px;\">"
                +"<h2> BADILI SACCO </h2>"
                +"<h6> Jipange uzeeni </h6>"
                +"<h2 >Login Form</h2>"

                + "<form   action=\"./login\" method=\"post\">"

                 +"<label>Action:</label> <input type=\\\"text\\\" name=\\\"action\\\" value=\\\"login\\\">>"
                + " <div class=\"container\">"
                +"<label ><b>Username</b></label>"
                +"<input type=\"text\" placeholder=\"Enter Username\">"

                +" <label ><b>Password</b></label>"
                +  "<input type=\"password\" placeholder=\"Enter Password\">"

                + "<button type=\"submit\">Login</button>"
                +"</div>"

                + "<div class=\"container\" style=\"background-color:#f1f1f1\">"

                + " <span class=\"rgs\">Forgot <a href=\"action\">password?</a></span>"
                + "</div>"
                + "<div>"
                + " <span class=\"rgs\">\"Not yet registered click to register: <a href=\"action\">register</a></span>"
                +"</div>"
                +"</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span>"
                +"</body>"
                +"</html>";


    }

    public String register(String actionError){
        return "<!DOCTYPE html>"
               +" <html>"
              +"<head>"
              +" <title>"
               +" Registration Page"
                + "<style>"
                +" h1 {text-align: center;}"
                +" h6 {text-align: center;}"
                +"</style>"
                +"</title>"
            +"</head>"
        +"<body bgcolor=\"Lightskyblue\" style=\"margin: auto; width: 220px;\">"

                +"<h2> BADILI SACCO </h2>"
                +"<h6> Jipange uzeeni </h6>"
                +"<h3 >REGISTRATION</h3>"

               +"<form action=\"./login\" method=\"post\">"
                + "<label> Action:</label>  <input type=\\\"text\\\" name=\\\"action\\\" value=\\\"register\\\">>"
            +"  <label> Firstname </label>"
             +"<input type=\"text\" placeholder=\"First Name\">"
                + "<br>"
             +"<label> Lastname: </label>"
         +"<input type=\"text\" placeholder=\"Last Name\">"
                + "<br>"
         +"<label> UserName: </label>"
          +"<input type=\"text\" placeholder=\"Enter Username\">"
    +"<br>"
    +"<label>"
      +" Gender :"
    +"</label><br>"
    +"<input type=\"radio\" name=\"male\"/> Male <br>"
    +"<input type=\"radio\" name=\"female\"/> Female <br>"
    +"<input type=\"radio\" name=\"other\"/> Other"
    +"<br>"
      +" Phone :"
    +"<input type=\"text\" name=\"phone\" > <br> <br>"
    +"Email:"
    +"<input type=\"email\" id=\"email\" name=\"email\"/> <br>"

      +" Password:"
    +"<input type=\"Password\" id=\"pass\" name=\"pass\"> <br>"

      +"ConfirmPassword:"
    +"<input type=\"Password\" id=\"repass\" name=\"repass\"> <br>"
    +"<input type=\"button\" value=\"Submit\"/><br>"
    +"<label>"
      +" Already registerd click to log in:"
    +"</label>"
    +"<input type=\"button\" value=\"LOGIN\"/>"
+"</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span>"
+"</body>"
+"</html>";
    }
    public String LandPage(){
        return "<!DOCTYPE html>"
                + "<html> "
               + "<style>"
               +" h1 {text-align: center;}"
        + "h6 {text-align: center;}"
             +"</style>"
                + "<body>"
                +"<h1>WELCOME TO BADILI SACCO </h1>"
                 +"<h6> Jipange uzeeni </h6>"
                + " To Register <a href='./welcome?action=register'>Register</a><br/>"
                + " To Loging <a href='./welcome?action=login'>Login</a><br/>"
                + "</body>"
                + "</html>";
    }


}