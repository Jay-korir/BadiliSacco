package actions.contribution;

import actions.Mail;
import bean.ContributionBeanI;
import model.Contribution;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.SessionBean;
import javax.mail.Session;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/contribution")
public class ContributionAction extends HttpServlet {

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
//        System.out.println("==group By ===");
//        contributionBean.groupBy();
//        System.out.println("++order by ===");
//        contributionBean.orderBy();
//        System.out.println("==limit==");
//        contributionBean.limit();
//        System.out.println("====innerJoin");
//        contributionBean.inner();
//        System.out.println("======leftJoin");
//        //contributionBean.leftJoin();
//        System.out.println("===========crossJoin");
//        contributionBean.crossJoin();

        System.out.println("reports==" + contributionBean.contributionReport(contribution.getType()));

        try {
            contributionBean.add(contribution);
            res.sendRedirect("./contributionPage.jsp");
        } catch (Exception e) {
            servletCtx.setAttribute("addError", e.getMessage());
            res.sendRedirect("./addContribution.jsp");
        }


    }
    @Schedules({
//            @Schedule(dayOfMonth="23"),
            @Schedule(dayOfWeek="wednesday", hour="10",minute = "50")
    })
    public void doPeriodicCleanup() throws Exception { contributionBean.getTotalContribution();
        System.out.println("======loan as at 10:40==" +contributionBean.getTotalContribution());
    }

}

