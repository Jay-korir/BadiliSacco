package bean;

import model.Loan;

import java.util.List;

public interface LoanBeanI {
    void add(Loan loan) throws Exception;

    List<Loan> list() throws Exception;

    void update(Loan loan) throws Exception;

    void decline(Loan loan) throws Exception;

    double totalUserLoan(String username) throws Exception;

    double totalPay(String username) throws Exception;

    double getTotalLoan() throws Exception;

    double getTotalPay() throws Exception;

    //List<Loan> list(Loan filter) throws Exception;

}
