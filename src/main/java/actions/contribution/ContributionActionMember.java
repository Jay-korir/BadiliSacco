package actions.contribution;

import bean.ContributionBeanI;
import model.Contribution;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/memberContribution")
public class ContributionActionMember extends HttpServlet {
    @EJB
    ContributionBeanI contributionBean;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        Contribution contribution = new Contribution();
        try {
            BeanUtils.populate(contribution, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(contribution);

        System.out.println("=====user contribution ==" + contributionBean.getListUser(contribution.getUsername()));
        try {
            contributionBean.add(contribution);
            res.sendRedirect("./userDashboard.jsp");
        } catch (Exception e) {
            servletCtx.setAttribute("addError",e.getMessage());
            res.sendRedirect("./memberContribution.jsp");
        }



    }

}


