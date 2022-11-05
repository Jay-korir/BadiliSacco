package controller;

import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthController {
    @Resource(lookup= "java:jboss/datasources/sacco")
    DataSource dataSource;
    @PersistenceContext
    EntityManager em;

    public User login(String username, String password ){
        User user = null;

        try {
            Connection connection = dataSource.getConnection();
            System.out.println("connection created");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from login where username='" + username + "' and " + "password='" + DigestUtils.md2Hex(password) + "' ");
            while (resultSet.next()){
                user = new User();

                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(resultSet.getString("usertype"));


            }
        } catch (SQLException e) {
            System.out.println("error "+ e.getMessage());
        }
        return user;
    }
}
