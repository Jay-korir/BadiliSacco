package actions.contribution;


import controller.ContributionBean;
import controller.ContributionBeanI;
import model.Contribution;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
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

@WebServlet("/updateContribution")

public class UpdateContribution extends HttpServlet {
    @EJB
    ContributionBeanI contributionBean;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ;
        resp.sendRedirect("./updateContribution.jsp");
    }

    @SuppressWarnings("unchecked")
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        Contribution contribution = new Contribution();
        try {
            BeanUtils.populate(contribution, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        if (StringUtils.isBlank(contribution.getUsername())) {
            servletCtx.setAttribute("contributionError", "username is required");
            resp.sendRedirect("./addContribution.jsp");
            return;
        }

        if (StringUtils.isBlank(contribution.getMonth())) {
            servletCtx.setAttribute("contributionError", "month is required");
            resp.sendRedirect("./addContribution.jsp");
            return;
        }
        if (contribution.getAmount() == 0) {
            servletCtx.setAttribute("contributionError", "amount is required");
            resp.sendRedirect("./addContribution.jsp");
            return;
        }


        contribution.setUsername("username");
        contribution.setAmount(Double.parseDouble("amount"));
        contribution.setMonth("month");

       contributionBean.update(contribution);


        resp.sendRedirect("./contributionPage.jsp");
    }

}
