package service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reg extends Entity {

    private int Id;
    private String username;
    private String password;


    static ResultSet resultSet;
    private static final String tableName = "Login";

    private static final Map<String, Object> entitiesMap = new HashMap<String, Object>() {{
        put("Id", null);
        put("Username", "");
        put("Password", "");

    }};
    ;

    public Reg() throws SQLException {
        super(entitiesMap, tableName);
    }

    public static IMYSQLDB<Reg> getLoginDB() throws SQLException {
        return new MYSQLSACCO<>(new Reg());
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        entitiesMap.put("Id", id);
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        entitiesMap.put("Username", username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        entitiesMap.put("Password", password);
        this.password = password;
    }


    public static List<Reg> displayAll() throws SQLException {
        List<Reg> studentList = new ArrayList<>();
        resultSet = getLoginDB().fetchAll();
        while (resultSet.next()) {
            Reg login = new Reg();
            login.setId(resultSet.getInt("id"));
            login.setUsername(resultSet.getString("username"));
            login.setPassword(resultSet.getString("password"));
            studentList.add(login);
        }
        return studentList;
    }

    @Override
    public String toString() {
        return "Login{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}