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
}
