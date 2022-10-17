package controller;

import model.Contribution;
import model.Members;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MembersController  implements Serializable {
    public void add(Connection connection, Members members){
        if ( members == null ||StringUtils.isBlank(members.getFirstName()) || StringUtils.isBlank(members.getLastName()) ||
                StringUtils.isBlank(members.getUserName())|| StringUtils.isBlank(members.getEmail())||StringUtils.isBlank(members.getPhone()))
            return;

        try {

            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO `members`(`firstname`, `lastname`, `username`, `email`, `phone`) " +
                    "VALUES ('"+ members.getFirstName().trim() +"','"+ members.getLastName().trim() +"','"+ members.getUserName().trim() +"','"+ members.getEmail().trim() +"','"+ members.getPhone().trim() +"')");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public List<Members> list(Connection connection, Members filter){
        List<Members> members = new ArrayList<Members>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from members");
            while (resultSet.next()){
                model.Members member = new model.Members();
                member.setFirstName(resultSet.getString("firstname"));
                member.setLastName(resultSet.getString("lastname"));
                member.setUserName(resultSet.getString("username"));
                member.setEmail(resultSet.getString("email"));
                member.setPhone(resultSet.getString("phone"));
                members.add(member);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }




}
