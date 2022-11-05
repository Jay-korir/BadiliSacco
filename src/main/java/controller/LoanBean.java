package controller;


import model.Loan;
import org.apache.commons.lang3.StringUtils;


import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import java.util.List;

@Stateless
@Remote
@Named("loanController")
public class LoanBean implements LoanBeanI {

    @PersistenceContext
    EntityManager em;


    public void add(Loan loan) {
        if (loan == null || StringUtils.isBlank(loan.getUsername()))
            return;
        if (loan == null || loan.getLoanAmount() == 0)
            return;
        if (loan== null || loan.getPeriod() == 0)
            return;
        if (loan == null || StringUtils.isBlank(loan.getPurpose()))
            return;

        em.merge(loan);

    }

    public List<Loan> getList()  {
       return  em.createQuery("From Loan l", Loan.class).getResultList();
    }
    public void update(Loan loan) {
em.persist(loan);
    }
    public void decline(Loan loan) {
em.persist(loan);
    }
}
