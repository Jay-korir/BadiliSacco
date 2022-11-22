package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection con = null;

    static {
        String url = "jdbc:mysql:// localhost:3306/login";
        String user = "root";
        String pass = "root";

        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

}

