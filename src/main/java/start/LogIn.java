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

    ServletConfig config = null;

    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }


static  String currentTime;

    @Override
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        String action = servletRequest.getParameter("action");

        String name = servletRequest.getParameter("name");
        System.out.println("welcome " + name);
        System.out.println("========");

        Enumeration<String> enumHeader = servletRequest.getHeaderNames();

        while (enumHeader.hasMoreElements()) {
            String nameH = (String) enumHeader.nextElement();
            String valueH = servletRequest.getHeader(nameH);

            System.out.println("Header name is ===" + nameH + " :" + "====header value is ====" + valueH);
        }
        int status = servletResponse.getStatus();
        System.out.println("status is ====" + status);
        System.out.println("==========");
        System.out.println("the header names of the response");


            System.out.println("====================");

            servletResponse.setIntHeader("Refresh", 30);


            Calendar calendar = new GregorianCalendar();
            String am_pm;
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);

            if (calendar.get(Calendar.AM_PM) == 0)
                am_pm = "AM";
            else
                am_pm = "PM";

            currentTime = hour + ":" + minute + ":" + second + " " + am_pm;
            System.out.println(currentTime);
            System.out.println("==========================");

        System.out.println("servlet response methods");

        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        System.out.println("character encoding " + servletResponse.getCharacterEncoding());
        System.out.println("content type  " + servletResponse.getContentType());
        System.out.println("BufferSize ==" + servletResponse.getBufferSize());
        System.out.println("committed : " + servletResponse.isCommitted());
        System.out.println("locale : " + servletResponse.getLocale());

            PrintWriter wr = servletResponse.getWriter();
            wr.print(this.login(null));
/*

            if (action != null && action.equalsIgnoreCase("register"))
                wr.print(this.register(null));

            else if (action != null && action.equalsIgnoreCase("login"))
                wr.print(this.login(null));

            else
                wr.print(this.LandPage());

        }


 */
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {


        System.out.println("getting the parameters");

        int status = res.getStatus();
        System.out.println("status is ====" + status);
        Enumeration<String> namesForm = req.getParameterNames();
        while (namesForm.hasMoreElements()) {
            String field = namesForm.nextElement();
            System.out.println("Field name : ===" + field + " the value is : " + req.getParameterValues(field)[0]);

        }
        System.out.println("======================");
        Map<String, String[]> stringMap = req.getParameterMap();
        for (Map.Entry<String, String[]> map : stringMap.entrySet()) {
            if (map.getValue() != null && map.getValue().length > 0)
                System.out.println(map.getKey() + "===========" + map.getValue()[0]);
        }




      /*  String action = req.getParameter("action");
        String first = req.getParameter("FirstName");
        String last = req.getParameter("LastName");
        String user = req.getParameter("UserName");
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

            System.out.println("Email:" + email);
            System.out.println("Phone:"+ phone);
            System.out.println("Password:"+ password);
            System.out.println("ConfirmPassword:" +conPassword);
            System.out.println("user successfully registered");
            this.login(actionError);

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
                  // res.sendRedirect("http://localhost:8080/BadiliSacco/");
                   res.sendRedirect("welcome");

            if (actionError.equals(""))
                wr.print(this.LandPage());

       else
           wr.print(this.register(actionError));

        }else if (login){


       */
        PrintWriter wr = res.getWriter();
        String user = req.getParameter("UserName");
        String password = req.getParameter("Password");
        String actionError = "";
        System.out.println("UserName: " + user);
        System.out.println("Password: " + password);
        System.out.println("successfully logged in ");

        if (user == null || user.equalsIgnoreCase("")){
             actionError = "username is required <br/>";
            wr.print(this.login("username  is required<br/>"));
        return;
    }
            if (password == null || password.equalsIgnoreCase("")) {
                actionError += " enter password";
                wr.print(this.login("password is required"));
            return;
            }

       // if (!user.equals(config.getInitParameter("username")) && !password.equals(config.getInitParameter("password"))) {
        //    wr.print(this.login("Invalid username & password combination<br/>"));
         //   return;
        //}


            if (password.equals("5055")){
                RequestDispatcher read = req.getRequestDispatcher("welcome");
                read.forward(req, res);
            }

            if (password != null && !password.equals("5055")) {
                actionError += "username or wrong password";
                wr.print(this.login("username or wrong password<br/>"));
           return;
            }
        HttpSession session = req.getSession(true);
        session.setAttribute("loggedInTime","time logged in time :" + new Date());

        List<String>  activities = new ArrayList<>();
        activities.add("Contribution");
        activities.add("savings");
        activities.add("loans");
        activities.add("Expenses");
        activities.add("reports");

        session.setAttribute("activity", activities);



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
               + "<h1>" + config.getServletContext().getInitParameter("applicationLabel") + "</h1>"
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
                +  " Not yet registered click to register: <a href='./login?action=register'>Register</a><br/>"

                +"</div>"
                +"</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span>"
                +"</body>"
                +"</html>";


    }
}
    /*
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
                + "Click to Register <a href='./login?action=register'>Register</a><br/>"
                + "Click  to Login <a href='./login?action=login'>Login</a><br/>"
                + "</body>"
                + "</html>";
    }




}

     */
