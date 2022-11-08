package controller;

import model.Contribution;
import org.apache.commons.lang3.StringUtils;


import javax.ejb.Remote;
import javax.ejb.Stateless;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

 @Stateless
 @Remote
 @Named("contributionController")
public class ContributionBean implements ContributionBeanI {

    @PersistenceContext
    EntityManager em;


    public void add(Contribution contribution) {
        if (contribution == null || StringUtils.isBlank(contribution.getUsername()))
            return;
        if (contribution == null || StringUtils.isBlank(contribution.getMonth()))
            return;
        if (contribution == null || contribution.getAmount() == 0)
            return;

       if (contribution.getId() != null)
            System.out.println("Entity manager will perform an update");

        em.merge(contribution);


    }






//    public List<Contribution> list(Contribution filter) {
//        List<Contribution> contributions = new ArrayList<Contribution>();
//
//
//        return contributions;
//    }


     public void delete(Long contributionId) {
         System.out.println(this.getContribution(contributionId));
         em.remove(em.find(Contribution.class, contributionId));

    }
     public List<Contribution> getList() {
         return em.createQuery("FROM Contribution s", Contribution.class).getResultList();
     }


     public Contribution getContribution(Long id) {
         return em.createQuery("FROM Contribution s WHERE s.id =:Id", Contribution.class)
                 .setParameter("Id",id)
                 .getResultList().get(0);
     }


     public  void update(Contribution contribution) {
  em.persist(contribution);

    }


    public int getTotalContribution() {

        int result = 0;


        return result;
    }


    public int totalUserContribution(String username) {

        int result = 0;


        return result;
    }


    public Contribution getUserContribution(int id) {
        Contribution contribution = new Contribution();


        return contribution;
    }





    public int getList1() {
        int result = 0;

        return result;
    }



}

