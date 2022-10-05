package start;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;



public class LogIn extends HttpServlet {



static  String currentTime;

    @Override
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
            System.out.println("====================");
            servletResponse.setIntHeader("Refresh", 30);
            PrintWriter wr = servletResponse.getWriter();
            wr.print(this.login(null));

    }

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
  System.out.println("===========================");
        PrintWriter wr = res.getWriter();
        String user = req.getParameter("UserName");
        String password = req.getParameter("Password");
        System.out.println("UserName: " + user);
        System.out.println("Password: " + password);
        System.out.println("successfully logged in ");

        if (user == null || user.equalsIgnoreCase("")){
            wr.print(this.login("username  is required<br/>"));
        return;
    }
            if (password == null || password.equalsIgnoreCase("")) {
                wr.print(this.login("password is required"));
            return;
            }


            if (!user.equals(getServletConfig().getInitParameter("user")) && !password.equals(getServletConfig().getInitParameter("password"))) {
                wr.print(this.login("Invalid username & password combination<br/>"));
                return;
            }

        HttpSession session = req.getSession(true);
        session.setAttribute("loggedInTime"," logged in time :" + new Date());

        List<String>  activities = new ArrayList<>();
        activities.add("Contribution");
        activities.add("savings");
        activities.add("loans");
        activities.add("Expenses");
        activities.add("reports");

        session.setAttribute("activity", activities);


        System.out.println(session.getId());
        System.out.println(session.getCreationTime());
        System.out.println(session.getLastAccessedTime());

        RequestDispatcher read = req.getRequestDispatcher("welcome");
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
                + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                +"<h2> BADILI SACCO </h2>"
                +"<h6> Jipange uzeeni </h6>"
                +"<h2 >Login Form</h2>"


                + "<form   action=\"./login\" method=\"post\">"

                 +"<label>Action:</label> <input type=\"text\" name=\"action\" value=\"login\">"
                + " <div class=\"container\">"
                +"<label ><b>Username</b></label>"
                +"<input type=\"text\" name= \"UserName\" required placeholder=\"Enter Username\">"

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
}
