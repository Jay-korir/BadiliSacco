package controller;

import model.Members;
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
@Named("memberController")
public class MembersController  implements Serializable {

    public MembersController() {
    }

    @Resource(lookup= "java:jboss/datasources/sacco")
    DataSource dataSource;

    private List<Members> list;
    public void add(Members members){
        if ( members == null ||StringUtils.isBlank(members.getFirstname()) || StringUtils.isBlank(members.getLastname()) ||
                StringUtils.isBlank(members.getUsername())|| StringUtils.isBlank(members.getEmail())||StringUtils.isBlank(members.getPhone()))
            return;

        try {

            Statement statement = dataSource.getConnection().createStatement();
            statement.execute("INSERT INTO members(firstname, lastname, username, email, phone) " +
                    "VALUES ('"+ members.getFirstname().trim() +"','"+ members.getLastname().trim() +"','"+ members.getUsername().trim()+"','"+
                    members.getEmail().trim() +"','"+ members.getPhone().trim()  +"')");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public List<Members> list(Members filter){
        List<Members> members = new ArrayList<Members>();

        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from members");
            while (resultSet.next()){
                model.Members member = new model.Members();
                member.setFirstname(resultSet.getString("firstname"));
                member.setLastname(resultSet.getString("lastname"));
                member.setUsername(resultSet.getString("username"));
                member.setEmail(resultSet.getString("email"));
                member.setPhone(resultSet.getString("phone"));
                members.add(member);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    public void update(Members members) {
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.executeUpdate("UPDATE members " + "SET" + "firstname  = '" + members.getFirstname() + "'," +
                    "lastname = '" + members.getLastname() + "'," +
                    "username = '" + members.getUsername() + "'" +
                    "email = '" + members.getEmail() + "'" +
                    "phone = '" + members.getPhone() + "'" +
                    "WHERE " + "username=" + "'" + members.getUsername() + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Members members) {
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.executeUpdate("delete from members where id=" + "'" + members.getId() + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Members getMember(int id) {
         Members members = new Members();

        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from members where id =" +"'" + id + "'");
            while (resultSet.next()) {

                members.setFirstname(resultSet.getString("firstname"));
                members.setLastname(resultSet.getString("lastname"));
                members.setUsername(resultSet.getString("username"));
                members.setEmail(resultSet.getString("email"));
                members.setPhone(resultSet.getString("phone"));


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    public List<Members> getList() {
        List<Members> members = new ArrayList<Members>();

        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from members");
            while (resultSet.next()){
                model.Members member = new model.Members();
                member.setFirstname(resultSet.getString("firstname"));
                member.setLastname(resultSet.getString("lastname"));
                member.setUsername(resultSet.getString("username"));
                member.setEmail(resultSet.getString("email"));
                member.setPhone(resultSet.getString("phone"));
                member.setId(resultSet.getInt("id"));
                members.add(member);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    public void setList(List<Members> list) {
        this.list = list;
    }
}