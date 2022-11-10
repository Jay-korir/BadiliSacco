package actions.contribution;


import bean.ContributionBeanI;
import model.Contribution;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteContribution")

public class DeleteContribution extends HttpServlet {
    @EJB
    ContributionBeanI contributionBean;

    static ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Contribution contribution = new Contribution();
        contribution.setId(Long.valueOf(Integer.parseInt(req.getParameter("id"))));

        contributionBean.delete(contribution.getId());
        res.sendRedirect("./contributionPage.jsp");
    }


}