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

@WebServlet("/updateContribution")

public class UpdateContribution extends HttpServlet {
    @EJB
    ContributionBeanI contributionBean;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//
//        resp.sendRedirect("./updateContribution.jsp");
//    }

    @SuppressWarnings("unchecked")
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Contribution contribution = new Contribution();
        System.out.println("===================");
        try {
            BeanUtils.populate(contribution, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(contribution);



        try {
            contributionBean.update(contribution);
            resp.sendRedirect("./contributionPage.jsp");
        } catch (Exception e) {
            servletCtx.setAttribute("addError",e.getMessage());
        }



    }

}
