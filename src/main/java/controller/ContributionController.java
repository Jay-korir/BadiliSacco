package controller;

import model.Contribution;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named("contributionController")
public class ContributionController  implements Serializable {

    @Resource(lookup= "java:jboss/datasources/sacco")
    DataSource dataSource;

    private List<Contribution> list;
    private List<Contribution> list1;
    public void add(Contribution contribution) {
        if (contribution == null || StringUtils.isBlank(contribution.getUsername()))
            return;
        if (contribution == null || StringUtils.isBlank(contribution.getMonth()))
            return;
        if (contribution == null || contribution.getAmount() == 0)
            return;

        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();
            System.out.println("insert into contribution(username,month,amount) " +
                    "values('" + contribution.getUsername() + "','" + contribution.getMonth() + "','" + contribution.getAmount() + "','" +
                    +contribution.getId() + "')");
            sqlStmt.execute("insert into contribution(username,month,amount) " +
                    "values('" + contribution.getUsername() + "','" + contribution.getMonth() + "','" + contribution.getAmount()
                    + "')");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    public List<Contribution> list(Contribution filter) {
        List<Contribution> contributions = new ArrayList<Contribution>();

        try {
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
        return contributions;
    }

    public void delete(Contribution contribution) {
        try {
            Statement statement = dataSource.getConnection().createStatement();
            System.out.println("==============================");
            System.out.println("delete from contribution where username=" + "'" + contribution.getUsername() + "'");
            statement.executeUpdate("delete from contribution where id=" + "'" + contribution.getId() + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Contribution contribution) {
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.executeUpdate("UPDATE contribution " + "SET" + "username  = '" + contribution.getUsername() + "'," +
                    "month = '" + contribution.getMonth() + "'," +
                    "amount = '" + contribution.getAmount() + "'" +
                    "WHERE " + "username=" + "'" + contribution.getUsername() + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getTotalContribution() {

        int result = 0;
        try {

            Statement statement = dataSource.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT SUM(amount) as totalAmount FROM `contribution`");
            ResultSet resultSet1 = statement.executeQuery("SELECT amount FROM `contribution`");
            System.out.println("=================");
            if(resultSet1 != null) {
                while(resultSet1.next()) {
                    result += (int) resultSet1.getDouble("amount");
                }
            }
            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int totalUserContribution(String username) {

        int result = 0;
        try {

            Statement statement = dataSource.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT SUM(amount) as totalAmount FROM `contribution`");
            ResultSet resultSet1 = statement.executeQuery("SELECT amount FROM `contribution` where username=" + "'" + username + "'");
            System.out.println("=================");
            if(resultSet1 != null) {
                while(resultSet1.next()) {
                    result += (int) resultSet1.getDouble("amount");
                }
            }
            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    public Contribution getContribution(int id) {
        Contribution contribution = new Contribution();

        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contribution where id =" +"'" + id + "'");
            while (resultSet.next()) {

                contribution.setUsername(resultSet.getString("username"));
                contribution.setMonth(resultSet.getString("month"));
                contribution.setAmount(resultSet.getDouble("amount"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contribution;
    }

    public List<Contribution> getList() {
        List<Contribution> contributions = new ArrayList<Contribution>();

        try {
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
        return contributions;
    }

    public void setList(List<Contribution> list) {
        this.list = list;
    }

    public int getList1() {
        int result = 0;
        try {

            Statement statement = dataSource.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT SUM(amount) as totalAmount FROM `contribution`");
            ResultSet resultSet1 = statement.executeQuery("SELECT amount FROM `contribution`");
            System.out.println("=================");
            if(resultSet1 != null) {
                while(resultSet1.next()) {
                    result += (int) resultSet1.getDouble("amount");
                }
            }
            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void setList1(List<Contribution> list1) {
        this.list1 = list1;
    }
}