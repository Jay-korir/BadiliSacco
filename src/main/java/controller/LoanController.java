package controller;


import model.Contribution;
import model.Loan;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoanController implements Serializable {
    public void add(Connection connection, Loan loan) {
        if (loan == null || StringUtils.isBlank(loan.getUsername()))
            return;
        if (loan == null || loan.getLoanAmount() == 0)
            return;
        if (loan== null || loan.getPeriod() == 0)
            return;
        if (loan == null || StringUtils.isBlank(loan.getPurpose()))
            return;;
        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into loan(username,appliedAmount,interest, totalpay, period,status) " +
                    "values('" + loan.getUsername() + "','" + loan.getLoanAmount() + "','" + loan.getInterest() + "','" +
                    loan.getTotalpay() + "','"  +loan.getPeriod() + "','"  +"pending" + "')");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    public List<Loan> list(Connection connection, Loan filter) {
        List<Loan> loans = new ArrayList<Loan>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from loan");
            while (resultSet.next()) {
                model.Loan loan = new model.Loan();
                loan.setUsername(resultSet.getString("username"));
                loan.setLoanAmount(resultSet.getDouble("appliedAmount"));
                loan.setInterest(resultSet.getDouble("interest"));
                loan.setTotalpay(resultSet.getDouble("totalpay"));
                loan.setPeriod(resultSet.getInt("period"));
                resultSet.getString("status");
                loans.add(loan);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return loans;
    }

}
