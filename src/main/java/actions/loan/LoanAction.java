package actions.loan;

import controller.ContributionBeanI;

import controller.LoanBeanI;
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
//        String currentUser = session.getAttribute("username").toString();
//        System.out.println(currentUser);
//        double loanApplied = Double.parseDouble(req.getParameter("loanAmount"));
//        System.out.println("myLoan===" + loanApplied);
//        int loanPeriod = Integer.parseInt(req.getParameter("period"));
//        System.out.println("loanPeriod =====" + loanPeriod);





        try {
            BeanUtils.populate(loan, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


//        System.out.println(loanApplied);
//        double myInterest = ((0.02) * loanApplied * loanPeriod);
//        loan.setInterest(myInterest);
//        System.out.println("myInterest=====" + myInterest);
//        ;
//
//        double myTotalPay = myInterest + loanApplied;
//        loan.setTotalPay(myTotalPay);
//        System.out.println("myTotalPay=====" + myTotalPay);
//        if (loanApplied <= (500000 / 2)) {

        System.out.println("==============");
        double userContribution = contributionBean.totalUserContribution(loan.getUsername());
        System.out.println(userContribution);
        loan.setUserContribution(userContribution);
        System.out.println("+++++++++" +this.totalUserContribution());
        try {
            System.out.println("=======" + loanBean.totalUserLoan(loan.getUsername()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
                loanBean.add(loan);
                resp.sendRedirect("./loan_up.jsp");
            } catch (Exception e) {
                servletCtx.setAttribute("loanError", e.getMessage());
            }




    }
    public double totalUserContribution() {
      return   contributionBean.totalUserContribution(loan.getUsername()) + loan.getLoanAmount();

    }


}

