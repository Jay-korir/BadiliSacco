package bean;


import model.Contribution;
import model.Loan;
import model.Members;
import org.apache.commons.lang3.StringUtils;


import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import java.util.List;

import static actions.LogIn.loggedUser;

@Stateless
@Remote
public class LoanBean implements LoanBeanI {

    @EJB
    ContributionBeanI contributionBean;

    @PersistenceContext
    EntityManager em;

    Loan loan = new Loan();

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


        double myTotalPay = myInterest + loanApplied;
        loan.setTotalPay(myTotalPay);
        System.out.println("myTotalPay=====" + myTotalPay);


        System.out.println("====loanbean====" + loan);


        if (loggedUser.equals("admin")) {
            loan.setStatus("Approved");
        } else if (loggedUser.equals("user")) {
            loan.setStatus("pending");
        }


        if (loanApplied <= (100000)) {
            System.out.println(loan);
            em.merge(loan);
        }
    }

    public void update(Loan loan) throws Exception {
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


        double myTotalPay = myInterest + loanApplied;
        loan.setTotalPay(myTotalPay);
        System.out.println("myTotalPay=====" + myTotalPay);

        loan.setStatus("Approved");

        System.out.println("====loanbean====" + loan);


        if (loanApplied <= (100000)) {
            System.out.println(loan);
            em.merge(loan);
        }
    }

    public double totalUserLoan(String username) {
        return (double) em.createQuery("Select sum(loanAmount) from Loan l WHERE l.username =:userName  " +
                        "and l.status =:Status")
                .setParameter("userName", username)
                .setParameter("Status", "Approved")
                .getSingleResult();
    }

    public List<Loan> loanReport(String status) {
        return em.createQuery("FROM Loan l WHERE l.status =:Type", Loan.class)
                .setParameter("Type", status)
                .getResultList();
    }

    public double totalPay(String username) {
        return (double) em.createQuery("Select sum(totalPay) from Loan l WHERE l.username =:userName " +
                        "and l.status =:Status")
                .setParameter("userName", username)
                .setParameter("Status", "Approved")
                .getSingleResult();
    }

    public double getTotalLoansPay() {

        return (double) em.createQuery("select sum(totalPay) from Loan l WHERE l.status =: Status")
                .setParameter("Status", "Approved")
                .getSingleResult();
    }

    public double getTotalLoan() {
        return (double) em.createQuery("select sum(loanAmount) from Loan l WHERE l.status =: Status")
                .setParameter("Status", "Approved")
                .getSingleResult();
    }

    public List<Loan> list() {
        return em.createQuery("From Loan l", Loan.class).getResultList();
    }

    public List<Loan> unApprovedList() {
        return em.createQuery("From Loan l where l.status =: Status").setParameter("Status", "pending").getResultList();
    }


    public void decline(Loan loan) {
        em.persist(loan);
    }

    public Loan getLoan(Long id) {
        return em.createQuery("FROM Loan s WHERE s.id =:Id", Loan.class)
                .setParameter("Id", id)
                .getResultList().get(0);
    }


}
