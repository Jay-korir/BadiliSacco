package controller;

import model.Contribution;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/control")
public class ContributionPage extends HttpServlet {
    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }


    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        res.getWriter().print("<!DOCTYPE html>"
                + "<html> "
                + "<head> "
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./assets/CSS/style.css\"/>"
                + "</head>"
                + "<body>"
                + "<div class=\"bg-img\">"
                + "<div class=\"content\">"
               // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<br/>" + contributionGrid(new Contribution())
                + "<p><a href='./back'><button class=\"back\">Logout</button></a></p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>");
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        res.getWriter().print("<!DOCTYPE html>"
                + "<html> "
                + "<head> "
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./assets/CSS/style.css\"/>"
                + "</head>"
                + "<body bgcolor=\"Lightskyblue\">"
                + "<div class=\"bg-img\">"
                + "<div class=\"content\">"
               // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<h2> Welcome: " + session.getAttribute("username") + "  Logged In At: " + session.getAttribute("loggedInTime") + "</h2>"
                + "<br/>" + contributionGrid(new Contribution())

                + "<p><a href='./contribution'><button class=\"button\">Add Vehicle</button></a> <a href='./logout'><button class=\"button\">Logout</button></a></p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>");
    }

    public String contributionGrid(Contribution vehicleFilter) {
        List<Contribution> contrib = new ArrayList<Contribution>();
        try {
            Connection connection = (Connection) servletCtx.getAttribute("myConnection");
            Statement sqlStmt = connection.createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from contribution");
            while (result.next()) {
                Contribution contribution = new Contribution();
                contribution.setUsername(result.getString("username"));
                contribution.setMonth(result.getString("month"));
                contribution.setAmount(result.getDouble("amount"));

                contrib.add(contribution);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        String contributionTable =

                "<table >" +
                        "<tr>" +
                        "<th>  username</th>" +
                        "<th>month</th>" +
                        "<th>amount </th>" +


                        "<th></th>" +
                        "</tr>";

        for (Contribution contribs : contrib)
            contributionTable += "<tr>"
                    + "<td>" + contribs.getUsername() + "</td>"
                    + "<td>" + contribs.getMonth() + "</td>"
                    + "<td>" + contribs.getAmount() + "</td>"


                    + "<td><a href=\"./updateContribution?id=" + contribs.getId() + "\">Edit</a>   | <a href=\"./deleteContribution?id=" + contribs.getId() + "\">Delete </td>"
                    + "</tr>";

        contributionTable += "</table>";

        return contributionTable;

    }
}