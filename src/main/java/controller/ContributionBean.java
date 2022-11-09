package controller;

import model.Contribution;
import model.Members;
import org.apache.commons.lang3.StringUtils;


import javax.ejb.Remote;
import javax.ejb.Stateless;


import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

 @Stateless
 @Remote
 @Named("contributionController")
 @TransactionManagement(TransactionManagementType.CONTAINER)
public class ContributionBean implements ContributionBeanI {

    @PersistenceContext
    EntityManager em;


    public void add(Contribution contribution) throws Exception {
        if (contribution == null || StringUtils.isBlank(contribution.getUsername()))
            throw new Exception("username required");
        if (contribution == null || StringUtils.isBlank(contribution.getMonth()))
            throw new Exception("month required");
        if (contribution == null || contribution.getAmount() == 0)
            throw new Exception("amount required");


        for (Contribution contribution1: getListUser(contribution.getUsername())) {
            if (contribution1.getUsername().equals(contribution.getUsername()) && (contribution1.getMonth().equals(contribution.getMonth())))
                System.out.println("month already contributed");
            return;

        }

        em.merge(contribution);


    }

     public void update(Contribution contribution) throws Exception {
         if (contribution == null || StringUtils.isBlank(contribution.getUsername()))
             throw new Exception("username required");
         if (contribution == null || StringUtils.isBlank(contribution.getMonth()))
             throw new Exception("month required");
         if (contribution == null || contribution.getAmount() == 0)
             throw new Exception("amount required");




         em.merge(contribution);


     }



     public void delete(Long contributionId) {
         System.out.println(this.getContribution(contributionId));
         em.remove(em.find(Contribution.class, contributionId));

    }
     public List<Contribution> getList() {
         return em.createQuery("FROM Contribution s", Contribution.class).getResultList();
     }

     public List<Contribution> getListUser(String username) {
         return em.createQuery("FROM Contribution c WHERE c.username =:userName", Contribution.class)
                 .setParameter("userName",username)
                 .getResultList();
     }

     public Contribution getContribution(Long id) {
         return em.createQuery("FROM Contribution s WHERE s.id =:Id", Contribution.class)
                 .setParameter("Id",id)
                 .getResultList().get(0);
     }



    public double getTotalContribution() {
        return (double) em.createQuery("select sum(amount) from Contribution").getSingleResult();
    }


    public double totalUserContribution(String username) {
      return (double) em.createQuery("Select sum(amount) from Contribution c WHERE c.username =:userName ")
                .setParameter("userName", username).getSingleResult();
    }
    }












