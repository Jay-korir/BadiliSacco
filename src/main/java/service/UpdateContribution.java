package service;


import model.Contribution;
import model.Members;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static controller.WelcomeServlet.members;

@WebServlet("/updateContribution")

public class UpdateContribution extends HttpServlet {

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();

        int memberId = Integer.parseInt((req.getParameter("id")));
        System.out.println("member id : " + memberId);
        for (Members member : members) {
            if (member.getId() == memberId) {
               // resp.getWriter().print(this.editMember(null, member));
                break;
            }
        }

    }

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();


        String username = req.getParameter("username");
        String month = req.getParameter("email");
        Double amount = Double.valueOf(req.getParameter("amount"));

        int id = Integer.parseInt(req.getParameter("id"));
        Contribution memb = new Contribution();
        try {
            BeanUtils.populate(memb, req.getParameterMap());
        } catch (Exception ex) {
            System.out.println("bean util error " + ex.getMessage());
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(memb.getUsername())) {
            wr.print(this.editMember(" username is required<br/>", memb));
            return;
        }
        if (StringUtils.isBlank(memb.getMonth())) {
            wr.print(this.editMember("month is required", memb));
            return;
        }
        if (memb.getAmount() == 0) {
            wr.print(this.editMember("amount is required", memb));
            return;
        }

        HttpSession session = req.getSession();
        //members = (List<Members>) session.getAttribute("members");

        List<Contribution> contrib = new ArrayList<Contribution>();

        for (Contribution contribution : contrib) {
            if (contribution.getId() == Integer.parseInt(String.valueOf(id))) {
                contribution.setUsername(username);
                contribution.setMonth(month);
                contribution.setAmount(amount);
                break;
            }
        }
        resp.sendRedirect("./control");

    }

    public String editMember(String actionError, Contribution member) {
        return "<!DOCTYPE html>"
                + "<html> "
                + "<head> "
                + "</head>"
                + "<body bgcolor=\"Lightskyblue\"  >"
                // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<h2> Edit Member</h2>"
                + "<form action=\"./updateContribution?id=" + member.getId() + "\" method=\"post\">"
                + "<table> "

                + "<tr> <td> User Name: </td> <td> <input type=\"text\"name= \"username\" value=" + member.getUsername() + ""
                + "> </td> </tr> "
                + "<tr> <td> month: </td> <td> <input type=\"text\"name= \"month\" value=" + member.getMonth() + ""
                + "> </td> </tr> "
                + "<tr> <td> Amount: </td> <td> <input type=\"number\"name= \"amount\" value=" + member.getAmount() + ""
                + "> </td> </tr> "
                + "<tr> <td> <input type=\"submit\" value=\"Submit\"></tr> "
                + "</table>"
                + "</form>"
                + "<span style=\"color:red\">" + (actionError != null ? actionError : "") + "</span><br/>"
                + "Home? <a href='./control'>Back</a><br/>"
                + "</body>"
                + "</html>";
    }


    public String ContributionGri(int id) {
        List<Contribution> contribution = new ArrayList<Contribution>();
        try {
            Connection connection = (Connection) servletCtx.getAttribute("myConnection");
            Statement sqlStmt = connection.createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from contribution where id=?");
            while (result.next()) {
                Contribution contribution1 = new Contribution();
                contribution1.setUsername(result.getString("username"));
                contribution1.setMonth(result.getString("month"));
                contribution1.setAmount(result.getDouble("amount"));

                contribution.add(contribution1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        String contributionTable =

                "<table >" +
                        "<tr>" +
                        "<th>  username</th>" +
                        "<th>month</th>" +
                        "<th>amount </th>" +


                        "<th></th>" +
                        "</tr>";

        for (Contribution contribs : contribution)
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
