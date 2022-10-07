package controller;

import model.Members;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    HttpSession httpSession;

public static List<Members> members;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         httpSession = req.getSession();
         PrintWriter pw = resp.getWriter();
         pw.print(this.Welcome());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       httpSession  = req.getSession();
        if (httpSession == null || httpSession.getId() == null)
            resp.sendRedirect("/");
        PrintWriter write = resp.getWriter();
        write.print(this.Welcome());

    }


    public String Welcome(){
        return "<!DOCTYPE html>"
                + "<html> "
                + "<style>"
                +" h4 {text-align: center;}"
                + "h6 {text-align: center;}"
                +"</style>"
                + "<head>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Css/table.css\"/>"
                + "</head>"
                + "<body bgcolor=\"Lightskyblue\" style=\"margin: auto; width: auto;\">"
               // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                +"<h4>WELCOME TO BADILI SACCO </h4>"
                +"<h6> Jipange uzeeni </h6>"
                + "<form action=\"./welcome\" method=\"post\">"
              // + "<h1>" + getServletContext().getInitParameter("applicationLabel") + "</h1>"
               + "<h2> Welcome: " + httpSession.getAttribute("username")
              // + "<h2>   Logged In At: " + httpSession.getAttribute("loggedInTime") + "</h2>"
               // + "<span style=\"color:green;font-size: 24px;font-weight:bold\">Logged In</span>"
                + "<br/>MEMBERS <a href='./add'>Add Members</a><br/>"
                 + "<br/>" + membersList((List<Members>) httpSession.getAttribute("members"))
                + "<br/>"
                + "<br/>Logout <a href='./logout'>Logout</a><br/>"
              //  +" <a href='./logout</a><br/>n'>L"
                  +"</form>"
                + "</body>"
                + "</html>";
    }

    public static String membersList(List<Members> members){
        if(members == null)
            members = new ArrayList<Members>();
        String membersList = "<table >" +
                "<tr>" +
                "<th>FirstName</th>" +
                "<th>LastName</th>" +
                "<th>username</th>" +
                "<th>Email</th>" +
                "<th>Phone Number</th>" +
                "</tr>";
for (Members member : members)
    membersList += "<tr>"
            + "<td>" + member.getFirstName() + "</td>"
            + "<td>" + member.getLastName() + "</td>"
            + "<td>" + member.getUserName() + "</td>"
            + "<td>" + member.getEmail() + "</td>"
            + "<td>" + member.getPhone() + "</td>"
            + "<td><a href=\"./delete?id=" + member.getId() +"\">Delete</a>  |" +
            " <a href=\"./edit?id=" + member.getId() + "\">Edit</a></td>"
            + "</tr>";

        membersList += "</table>";
        return membersList;
    }
}

