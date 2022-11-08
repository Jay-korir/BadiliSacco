package actions.loan;

import controller.ContributionBeanI;

import controller.LoanBeanI;
import model.Loan;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

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


@WebServlet("/loan")
public class LoanAction extends HttpServlet {
//    @EJB
//    ContributionBeanI contributionBean;

    @EJB
    LoanBeanI loanBean;


    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String currentUser = session.getAttribute("username").toString();
        System.out.println(currentUser);
        double loanApplied = Double.parseDouble(req.getParameter("loanAmount"));
        System.out.println("myLoan===" + loanApplied);
        int loanPeriod = Integer.parseInt(req.getParameter("period"));
        System.out.println("loanPeriod =====" + loanPeriod);
        Loan loan = new Loan();


//        contributionBean.totalUserContribution(currentUser);
//          double myContribution =  contributionBean.totalUserContribution( currentUser);
//
//        System.out.println("==========");
//        System.out.println(myContribution);

        try {
            BeanUtils.populate(loan, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (StringUtils.isBlank(loan.getUsername())) {
            servletCtx.setAttribute("loanError", "username is required");
            resp.sendRedirect("./loan.jsp");
            return;
        }
        if (loan.getLoanAmount() == 0) {
            servletCtx.setAttribute("loanError", "amount is required");
            resp.sendRedirect("./loan.jsp");
            return;
        }
        if (loan.getPeriod() == 0) {
            servletCtx.setAttribute("loanError", "payment is required");
            resp.sendRedirect("./loan.jsp");
            return;
        }
        if (StringUtils.isBlank(loan.getPurpose())) {
            servletCtx.setAttribute("loanError", "purpose of loan is required");
            resp.sendRedirect("./loan.jsp");
            return;
        }

        if (loanApplied > (500000 / 2)) {
            servletCtx.setAttribute("loanError", "exceeded your limit ");
            resp.sendRedirect("./loan.jsp");
            return;

        }
        System.out.println(loanApplied);
        double myInterest = ((0.02) * loanApplied * loanPeriod);
        loan.setInterest(myInterest);
        System.out.println("myInterest=====" + myInterest);
        ;

        double myTotalPay = myInterest + loanApplied;
        loan.setTotalPay(myTotalPay);
        System.out.println("myTotalPay=====" + myTotalPay);
        if (loanApplied <= (500000 / 2)) {

            try {
                loanBean.add(loan);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("./loan_up.jsp");
        }


    }


}

