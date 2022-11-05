package controller;

import model.Loan;

import java.util.List;

public interface LoanBeanI {
    void  add(Loan loan) throws Exception;
    List<Loan> getList() throws Exception;
    void update(Loan loan) throws Exception;
    void decline(Loan loan) throws Exception;

}
