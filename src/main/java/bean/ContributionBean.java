package bean;

import model.Contribution;
import model.Members;
import org.apache.commons.lang3.StringUtils;


import javax.ejb.Remote;
import javax.ejb.Stateless;


import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

 @Stateless
 @Remote
 @TransactionManagement(TransactionManagementType.CONTAINER)
public class ContributionBean implements ContributionBeanI {

    @PersistenceContext
    EntityManager em;


    public void add(Contribution contribution) throws Exception {
        if (contribution == null || StringUtils.isBlank(contribution.getUsername()))
            throw new Exception("username required");
        if (contribution == null || StringUtils.isBlank(contribution.getMonth()))
            throw new Exception("month required");
        if (contribution == null || StringUtils.isBlank(contribution.getType()))
            throw new Exception("type required");
        if (contribution == null || contribution.getAmount() == 0)
            throw new Exception("amount required");


//        for (Contribution contribution1: getListUser(contribution.getUsername())) {
//            if (contribution1.getUsername().equals(contribution.getUsername()) && (contribution1.getMonth().equals(contribution.getMonth())))
//                System.out.println("month already contributed");
//            return;
//
//        }
        System.out.println("=======" + contribution);
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
     public List<Contribution> list() {
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
        return (double) em.createQuery("Select sum(amount) from Contribution c WHERE c.type =:Type" )
                .setParameter("Type", "Daily/monthly")
                .getSingleResult();
    }



    public double totalUserContribution(String username) {
      return (double) em.createQuery("Select sum(amount) from Contribution c WHERE c.username =:userName " +
              "and c.type =:pwd")
                .setParameter("userName", username)
              .setParameter("pwd","Daily/monthly")
              .getSingleResult();
    }
     public double payPenalty() {
         return (double) em.createQuery("Select sum(amount) from Contribution c WHERE c.type =:Type" )
                 .setParameter("Type", "penalty")
                 .getSingleResult();
     }
     public double payLoan() {
         return (double) em.createQuery("Select sum(amount) from Contribution c WHERE c.type =:Type" )
                 .setParameter("Type", "payLoan")
                 .getSingleResult();
     }
    public void groupBy(){
        String hql="SELECT username ,sum(amount) FROM Contribution c GROUP BY c.username";
        Query query=  em.createQuery(hql);
        List<Object[]> list = query.getResultList();
        for (Object[] object:list ) {
            System.out.println(object[0] + "     " + object[1]);
        }
    }
    public void orderBy(){
        Query query = em.createQuery("SELECT c FROM Contribution c ORDER BY c.amount DESC");
        List<Contribution> resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }
    public void limit(){
        Query query = em.createQuery("SELECT m FROM Members m ORDER BY m.id DESC ").setMaxResults(4);
        List<Members> resultList = query.getResultList();
        resultList.forEach(System.out::println);

    }
    public void inner(){

        Query query = em.createQuery(" SELECT m FROM Members m INNER JOIN Contribution c ON m.username=c.username");
        List<Members> resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }

     public void leftJoin(){

         Query query = em.createQuery(" SELECT m.firstname, m.lastname, c.amount FROM Members m LEFT JOIN Contribution c ON m.username = c.username");
         List<Members> resultList = query.getResultList();
         resultList.forEach(System.out::println);
     }


     public void crossJoin() {
         String hql = "from Contribution c, Members m";
         Query query = em.createQuery(hql);
         List<Object[]> list = query.getResultList();
         for (Object[] object : list) {
             System.out.println(object[0] + "     " + object[1]);
         }
     }

    }












