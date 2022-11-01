package actions.loan;

import controller.ContributionController;
import controller.LoanController;
import model.Loan;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
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


@WebServlet("/loan")
public class LoanAction extends  HttpServlet {
    @Inject
    ContributionController contributionController;

    @Inject
    LoanController loanController;
    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUser = servletCtx.getAttribute("username").toString();
        System.out.println(currentUser);
        double loanApplied = Double.parseDouble(req.getParameter("loanAmount"));
        System.out.println("myLoan===" +loanApplied);
        int loanPeriod = Integer.parseInt(req.getParameter("period"));
        System.out.println("loanPeriod =====" + loanPeriod);
        Loan loan = new Loan();


        contributionController.totalUserContribution(currentUser);
          double myContribution =  contributionController.totalUserContribution( currentUser);

        System.out.println("==========");
        System.out.println(myContribution);

        try {
            BeanUtils.populate(loan, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (StringUtils.isBlank(loan.getUsername())) {
            servletCtx.setAttribute("loanError","username is required");
            resp.sendRedirect("./loan.jsp");
            return;
        }
        if (loan.getLoanAmount() == 0) {
            servletCtx.setAttribute("loanError","amount is required");
            resp.sendRedirect("./loan.jsp");
            return;
        }
        if (loan.getPeriod() == 0) {
            servletCtx.setAttribute("loanError","payment is required");
            resp.sendRedirect("./loan.jsp");
            return;
        }
        if (StringUtils.isBlank(loan.getPurpose())) {
            servletCtx.setAttribute("loanError","purpose of loan is required");
            resp.sendRedirect("./loan.jsp");
            return;
        }

        if (loanApplied >(myContribution / 2)) {
            servletCtx.setAttribute("loanError","exceeded your limit ");
            resp.sendRedirect("./loan.jsp");
            return;

        }
        System.out.println(loanApplied);
        double myInterest = ( (0.02) * loanApplied *  loanPeriod );
        loan.setInterest(myInterest);
        System.out.println("myInterest=====" + myInterest);
        ;

        double myTotalPay = myInterest + loanApplied;
        loan.setTotalPay(myTotalPay);
        System.out.println("myTotalPay=====" + myTotalPay);
        if (loanApplied <= (myContribution / 2)){

            loanController.add(loan);
            resp.sendRedirect("./loan_up.jsp");
        }


    }


}

