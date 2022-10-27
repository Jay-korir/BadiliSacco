package actions.contribution;


import controller.ContributionController;
import model.Contribution;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/deleteContribution")

public class DeleteContribution extends HttpServlet {
    @Inject
    ContributionController contributionController;

    static ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }
    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Contribution contribution = new Contribution();
        contribution.setUsername(req.getParameter("username"));
        Connection connection = (Connection) servletCtx.getAttribute("myConnection");

        contributionController.delete(contribution);
         res.sendRedirect("./contributionPage.jsp");
    }



}