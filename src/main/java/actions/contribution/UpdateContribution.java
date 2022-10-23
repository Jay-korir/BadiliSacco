package actions.contribution;


import controller.ContributionController;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static actions.WelcomeServlet.members;

@WebServlet("/updateContribution")

public class UpdateContribution extends HttpServlet {

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                 resp.sendRedirect("./updateContribution.jsp");

    }

    @SuppressWarnings("unchecked")
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContributionController contributionController = new ContributionController();
        Contribution contribution = new Contribution();
       // contribution.setUsername(req.getParameter("username"));
        Connection connection = (Connection) servletCtx.getAttribute("myConnection");
      contributionController.update(connection,contribution);
    }
}
