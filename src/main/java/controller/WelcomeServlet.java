package controller;

import model.Members;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    HttpSession httpSession;

    public static List<Members> members;

    ServletContext sCtx = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        sCtx = config.getServletContext();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        httpSession = req.getSession();
        PrintWriter pw = resp.getWriter();
        pw.print(this.Welcome());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //httpSession  = req.getSession();
        PrintWriter write = resp.getWriter();
        write.print(this.Welcome());


        List<Members> members1= new ArrayList<Members>();

        try {

            Connection connection = (Connection) sCtx.getAttribute("myConnection");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from members");
            while (resultSet.next()){

                Members members2 = new Members();
                members2.setFirstName(resultSet.getString("firstname"));
                members2.setLastName(resultSet.getString("lastname"));
                members2.setUserName(resultSet.getString("username"));
                members2.setEmail(resultSet.getString("email"));
                members2.setPhone(resultSet.getString("phone"));

                members1.add(members2);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        members = members1;

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
                //+ "<h2> Welcome: " + httpSession.getAttribute("username")
                // + "<h2>   Logged In At: " + httpSession.getAttribute("loggedInTime") + "</h2>"
                // + "<span style=\"color:green;font-size: 24px;font-weight:bold\">Logged In</span>"
                + "<br/>MEMBERS <a href='./add'>Add Members</a><br/>"
                + "<br/>" + membersList(new Members())
                + "<br/>"
                + "<br/>Logout <a href='./logout'>Logout</a><br/>"
                //  +" <a href='./logout</a><br/>n'>L"
                +"</form>"
                + "</body>"
                + "</html>";
    }

    public static String membersList(Members membersFilter){

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
                    " <a href=\"./edit?id=" + member.getId() + "\">Edit</a> |" +

                    " <a href=\"./contribution?id=" + member.getId() + "\">Contribution</a></td>" +
                    "</tr>";

        membersList += "</table>";
        return membersList;
    }
}