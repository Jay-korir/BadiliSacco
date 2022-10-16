package controller;

import model.Contribution;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet("/contribution")
public class ContributionAction extends HttpServlet {
    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //res.getWriter().print(this.addContribution(null));
        res.sendRedirect("./addContribution.jsp");
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter wr = res.getWriter();
        Contribution contribution = new Contribution();
        try {
            BeanUtils.populate(contribution, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
       // res.setContentType("text/html");


        if (StringUtils.isBlank(contribution.getUsername())) {
           // wr.print(this.addContribution("username is required<br/>"));
            servletCtx.setAttribute("loginError","username is required");
            res.sendRedirect("./addContribution.jsp");
            return;
        }

        if (StringUtils.isBlank(contribution.getMonth())) {
            //wr.print(this.addContribution("contribution  month is required<br/>"));
            servletCtx.setAttribute("loginError","month is required");
            res.sendRedirect("./addContribution.jsp");
            return;
        }
        if (contribution.getAmount() == 0) {
           // wr.print(this.addContribution("amount is required<br/>"));
            servletCtx.setAttribute("loginError","amount is required");
            res.sendRedirect("./addContribution.jsp");
            return;
        }

        this.insert(contribution);
        wr.println("added successfully");
        RequestDispatcher dispatcher = req.getRequestDispatcher("control");
        dispatcher.forward(req, res);

    }





    public String addContribution(String actionError){
        return "<!DOCTYPE html>"
                + "<html> "
                + "<head> "
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./assets/CSS/style.css\"/>"
                + "</head>"
                + "<style> </style>"
                + "<body bgcolor=\"Lightskyblue\">"
                +"<div class=\"bg-img\">"
                +"<div class=\"content\">"
                + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<h2>Contribution</h2>"
                + "<form action=\"./contribution\" method=\"post\">"
                + "<table> "
                + "<tr> <td>Username: </td> <td> <input type=\"text\" name=\"username\"> </td> </tr> "
                + "<tr> <td> month: </td> <td> <input type=\"text\" name=\"month\"> </td> </tr> "
                + "<tr> <td> amount: </td> <td> <input type=\"text\" name=\"amount\"> </td> </tr> "


                + "<tr> <td> <input class=\"button\" type=\"submit\" value=\"Submit\"></tr> "
                + "</table>"
                + "</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span><br/>"
                + "<a href='/dash'><button class=\"button\">Back</button></a>"
                + "</body>"
                +"</div>"
                +"</div>"
                + "</html>";
    }
    public void insert(Contribution contribution) {
        if (contribution == null)
            return;

        try {
            Connection connection = (Connection) servletCtx.getAttribute("myConnection");

            Statement sqlStmt = connection.createStatement();
            System.out.println("insert into contribution(username, month, amount) " +
                    "values('" + contribution.getUsername() + "','" + contribution.getMonth() + "','" + contribution.getAmount() + "')");
            sqlStmt.executeUpdate("insert into contribution(username, month, amount) " +
                    "values('" + contribution.getUsername() + "','" + contribution.getMonth() + "','" + contribution.getAmount() + "')");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }
}

