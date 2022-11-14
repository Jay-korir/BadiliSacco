package view;

import bean.LoanBeanI;
import model.Loan;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("loanView")
public class LoanView implements Serializable {
    @EJB
    private LoanBeanI loanBean;


    public List<Loan> getList() throws Exception {
        return loanBean.list();

    }
    public  double getMyLoan(String username) throws Exception {
        return loanBean.totalUserLoan(username);
    }
    public double getTotalPay(String username) throws Exception {
        return  loanBean.totalPay(username);
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
