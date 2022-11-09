package controller;


import model.Loan;
import model.Members;
import org.apache.commons.lang3.StringUtils;


import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import java.util.ArrayList;
import java.util.List;

@Stateless
@Remote
@Named("loanController")
public class LoanBean implements LoanBeanI {

    @PersistenceContext
    EntityManager em;



    public void add(Loan loan) throws Exception {
        if (loan == null || StringUtils.isBlank(loan.getUsername()))
            throw new Exception("username required");
        if (loan == null || loan.getLoanAmount() == 0)
            throw new Exception("loanAmount required");
        if (loan == null || loan.getPeriod() == 0)
            throw new Exception("loan period required");
        if (loan == null || StringUtils.isBlank(loan.getPurpose()))
            throw new Exception("loan purpose required");


        double loanApplied = Double.parseDouble(String.valueOf(loan.getLoanAmount()));
        System.out.println("myLoan===" + loanApplied);
        int loanPeriod = Integer.parseInt(String.valueOf(loan.getPeriod()));
        System.out.println("loanPeriod =====" + loanPeriod);

        System.out.println(loanApplied);
        double myInterest = ((0.02) * loanApplied * loanPeriod);
        loan.setInterest(myInterest);
        System.out.println("myInterest=====" + myInterest);
        ;

        double myTotalPay = myInterest + loanApplied;
        loan.setTotalPay(myTotalPay);
        System.out.println("myTotalPay=====" + myTotalPay);
        if (loanApplied <= (100000)) {

            em.merge(loan);
        }
    }
    public double totalUserLoan(String username) {
        return (double) em.createQuery("Select sum(loanAmount) from Loan l WHERE l.username =:userName ")
                .setParameter("userName", username).getSingleResult();
    }
    public double totalPay(String username) {
        return (double) em.createQuery("Select sum(totalPay) from Loan l WHERE l.username =:userName ")
                .setParameter("userName", username).getSingleResult();
    }

    public double getTotalPay() {
        return (double) em.createQuery("select sum(totalPay) from Loan").getSingleResult();
    }
    public double getTotalLoan() {
        return (double) em.createQuery("select sum(loanAmount) from Loan").getSingleResult();
    }

    public List<Loan> getList() {
        return em.createQuery("From Loan l", Loan.class).getResultList();
    }

    public void update(Loan loan) {
        em.persist(loan);
    }

    public void decline(Loan loan) {
        em.persist(loan);
    }

    public List<Loan> list(Loan filter) {
        List<Loan> loan = new ArrayList<Loan>();


        return loan;
    }
}
