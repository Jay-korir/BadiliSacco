package actions.contribution;

import controller.ContributionController;
import model.Contribution;

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
import javax.servlet.http.HttpSession;

@WebServlet("/display")
public class DisplayContributions extends HttpServlet {
    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUser = servletCtx.getAttribute("username").toString();
        System.out.println(currentUser);
       Contribution contribution = new Contribution();

        PrintWriter wr = resp.getWriter();
        ContributionController contributionController = new ContributionController();
        contributionController.totalContribution((Connection) servletCtx.getAttribute("myConnection"));
          wr.print(contributionController.totalContribution((Connection) servletCtx.getAttribute("myConnection")));



        System.out.println("==========");
        System.out.println(contributionController.totalUserContribution((Connection) servletCtx.getAttribute("myConnection"),currentUser));
        System.out.println();
        wr.print(contributionController.totalUserContribution((Connection) servletCtx.getAttribute("myConnection"),currentUser));

    }



}