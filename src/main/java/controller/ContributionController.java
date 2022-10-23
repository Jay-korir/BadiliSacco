package controller;

import model.Contribution;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContributionController  implements Serializable {
    public void add(Connection connection, Contribution contribution) {
        if (contribution == null || StringUtils.isBlank(contribution.getUsername()))
            return;
        if (contribution == null || StringUtils.isBlank(contribution.getMonth()))
            return;
        if (contribution == null || contribution.getAmount() == 0)
            return;

        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into contribution(username,month,amount) " +
                    "values('" + contribution.getUsername() + "','" + contribution.getMonth() + "','" +
                    +contribution.getAmount() + "')");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    public List<Contribution> list(Connection connection, Contribution filter) {
        List<Contribution> contributions = new ArrayList<Contribution>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contribution");
            while (resultSet.next()) {
                model.Contribution contribution = new model.Contribution();
                contribution.setUsername(resultSet.getString("username"));
                contribution.setMonth(resultSet.getString("month"));
                contribution.setAmount(resultSet.getDouble("amount"));
                contributions.add(contribution);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contributions;
    }

    public void delete(Connection connection, Contribution contribution) {
        try {
            Statement statement = connection.createStatement();
            System.out.println("==============================");
            System.out.println("delete from contribution where username=" + "'" + contribution.getUsername() + "'");
            statement.executeUpdate("delete from contribution where username=" + "'" + contribution.getUsername() + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Connection connection, Contribution contribution) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE contribution " + "SET" + "username  = '" + contribution.getUsername() + "'," +
                    "month = '" + contribution.getMonth() + "'," +
                    "amount = '" + contribution.getAmount() + "'" +
                    "WHERE " + "username=" + "'" + contribution.getUsername() + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int totalContribution(Connection connection) {

        int result = 0;
        try {

            Statement statement = connection.createStatement();

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

    public int totalUserContribution(Connection connection, String username) {

        int result = 0;
        try {

            Statement statement = connection.createStatement();

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
    public List<Contribution> listUser(Connection connection, String username) {
        List<Contribution> contributions = new ArrayList<Contribution>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contribution where username =" +"'" + username + "'");
            while (resultSet.next()) {
                model.Contribution contribution = new model.Contribution();
                contribution.setUsername(resultSet.getString("username"));
                contribution.setMonth(resultSet.getString("month"));
                contribution.setAmount(resultSet.getDouble("amount"));
                contributions.add(contribution);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contributions;
    }
}