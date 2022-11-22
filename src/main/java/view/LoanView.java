package view;

import bean.ContributionBeanI;
import bean.LoanBeanI;
import model.Contribution;
import model.Loan;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("loanView")
public class LoanView implements Serializable {
    @EJB
    private LoanBeanI loanBean;

    @EJB
    ContributionBeanI contributionBean;

    public List<Loan> getList() throws Exception {
        return loanBean.list();

    }

    public List<Loan> getLoanReport(String status) {
        return loanBean.loanReport(status);

    }

    public double getMyLoan(String username) throws Exception {
        return loanBean.totalUserLoan(username);
    }

    public double getTotalPay(String username) throws Exception {
        double pay = 0;
        if (contributionBean.payUserLoan(username) == 0) {
            pay = loanBean.totalPay(username);
        }
        if (contributionBean.payPenalty(username) == 0) {
            pay = loanBean.totalPay(username);
        }
        if (contributionBean.payUserLoan(username) > 0) {
            pay = loanBean.totalPay(username) - contributionBean.payUserLoan(username);
        }
        if (contributionBean.payPenalty(username) > 0) {
            pay = loanBean.totalPay(username) - contributionBean.payPenalty(username);
        } else {
            pay = loanBean.totalPay(username) - 0;
        }
        return pay;
    }

    public List<Loan> getUnapproved() throws Exception {
        return loanBean.unApprovedList();

    }

    public Loan getUserLoan(Long id) throws Exception {
        return loanBean.getLoan(id);
    }

    public double totalReceivables() throws Exception {
        return loanBean.getTotalLoansPay();
    }

    public double totalLoaned() throws Exception {
        return loanBean.getTotalLoan();
    }
}
