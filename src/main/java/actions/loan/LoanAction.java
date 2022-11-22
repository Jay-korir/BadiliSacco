package actions.loan;

import bean.ContributionBeanI;

import bean.LoanBeanI;
import model.Loan;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static actions.LogIn.loggedUser;


@WebServlet("/loan")
public class LoanAction extends HttpServlet {
//    @EJB
//    ContributionBeanI contributionBean;

    @EJB
    LoanBeanI loanBean;

    @EJB
    ContributionBeanI contributionBean;

    ServletContext servletCtx = null;

    Loan loan = new Loan();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);


        try {
            BeanUtils.populate(loan, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        System.out.println("==============");
        System.out.println("loanee===" + loan.getUsername());
        double userContribution = contributionBean.totalUserContribution(loan.getUsername());

        System.out.println("contr==" + userContribution);
        loan.setUserContribution(userContribution);
        System.out.println("+++++++++ total user contri" + this.totalUserContribution());
        System.out.println("loanAction=====" + loan);

        try {
            loanBean.add(loan);
            if (loggedUser.equals("user")) {
                resp.sendRedirect("./loan_up.jsp");
            } else
                resp.sendRedirect("./loanPage.jsp");
        } catch (Exception e) {
            servletCtx.setAttribute("loanError", e.getMessage());
            resp.sendRedirect("./loan.jsp");
        }


    }

    public double totalUserContribution() {
        return contributionBean.totalUserContribution(loan.getUsername()) + loan.getLoanAmount();

    }


}

