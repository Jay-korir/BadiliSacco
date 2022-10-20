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
    public void add(Connection connection, Contribution contribution){
        if (contribution == null || StringUtils.isBlank(contribution.getUsername()))
            return;
        if (contribution == null ||   StringUtils.isBlank(contribution.getMonth()))
            return;
        if (contribution ==  null || contribution.getAmount() == 0)
            return;

        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into contribution(username,month,amount) " +
                    "values('" + contribution.getUsername() + "','" + contribution.getMonth() + "','" +
                      + contribution.getAmount() + "')");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    public List<Contribution> list(Connection connection, Contribution filter){
        List<Contribution> contributions = new ArrayList<Contribution>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contribution");
            while (resultSet.next()){
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

    public void delete(Connection connection, Contribution contribution){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from departments where id=" +contribution.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    }

