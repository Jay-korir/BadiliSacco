package actions.contribution;

import controller.ContributionController;
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

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter wr = res.getWriter();
        Contribution contribution = new Contribution();
        try {
            BeanUtils.populate(contribution, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        if (StringUtils.isBlank(contribution.getUsername())) {
           servletCtx.setAttribute("addError","username is required");
            res.sendRedirect("./addContribution.jsp");
            return;
        }

        if (StringUtils.isBlank(contribution.getMonth())) {
            servletCtx.setAttribute("addError","month is required");
            res.sendRedirect("./addContribution.jsp");
            return;
        }
        if (contribution.getAmount() == 0) {
           servletCtx.setAttribute("addError","amount is required");
            res.sendRedirect("./addContribution.jsp");
            return;
        }

        ContributionController contributionController = new ContributionController();
        contributionController.add((Connection) servletCtx.getAttribute("myConnection"),contribution);

         res.sendRedirect("./contributionPage.jsp");



    }

}

