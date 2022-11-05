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

        em.merge(contribution);


    }






    public List<Contribution> list(Contribution filter) {
        List<Contribution> contributions = new ArrayList<Contribution>();


        return contributions;
    }


    public void delete(Contribution contribution) {
        em.remove(contribution);

    }


    public  void update(Contribution contribution) {
    // em.persist(contribution);
        List<Contribution> contributions =  em.createQuery("From Contribution where a.id: myId",Contribution.class)
             .setParameter("myId",contribution.getId()).getResultList();

    }


    public int getTotalContribution() {

        int result = 0;


        return result;
    }


    public int totalUserContribution(String username) {

        int result = 0;
       /* try {

            Statement statement = dataSource.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT SUM(amount) as totalAmount FROM `contribution`");
            ResultSet resultSet1 = statement.executeQuery("SELECT amount FROM `contribution` where username=" + "'" + username + "'");
            System.out.println("=================");
            if (resultSet1 != null) {
                while (resultSet1.next()) {
                    result += (int) resultSet1.getDouble("amount");
                }
            }
            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        */

        return result;
    }


    public Contribution getUserContribution(int id) {
        Contribution contribution = new Contribution();

       /* try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contribution where id =" + "'" + id + "'");
            while (resultSet.next()) {

                contribution.setUsername(resultSet.getString("username"));
                contribution.setMonth(resultSet.getString("month"));
                contribution.setAmount(resultSet.getDouble("amount"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        */
        return contribution;
    }


    public List<Contribution> getList() {
       // List<Contribution> contributions = new ArrayList<Contribution>();

        return em.createQuery("FROM Contribution s", Contribution.class).getResultList();

       /* try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contribution");
            while (resultSet.next()) {
                model.Contribution contribution = new model.Contribution();
                contribution.setUsername(resultSet.getString("username"));
                contribution.setMonth(resultSet.getString("month"));
                contribution.setAmount(resultSet.getDouble("amount"));
                contribution.setId(resultSet.getInt("id"));
                contributions.add(contribution);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        */

    }



    public int getList1() {
        int result = 0;
       /* try {

            Statement statement = dataSource.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT SUM(amount) as totalAmount FROM `contribution`");
            ResultSet resultSet1 = statement.executeQuery("SELECT amount FROM `contribution`");
            System.out.println("=================");
            if (resultSet1 != null) {
                while (resultSet1.next()) {
                    result += (int) resultSet1.getDouble("amount");
                }
            }
            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        */

        return result;
    }



}

