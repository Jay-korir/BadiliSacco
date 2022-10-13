package eventListeners;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("start of initialization ");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("applicationlabel","BADILI SACCO");

        try {
            System.out.println("connections starting -----");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sacco","root","");

           // MysqlDataSource dataSource = new MysqlDataSource();
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/sacco");
            dataSource.setUsername("root");
            dataSource.setPassword("");

            dataSource.setInitialSize(2);
            dataSource.setMaxIdle(2);
            dataSource.setMaxTotal(5);
            Connection connection = dataSource.getConnection();
        servletContext.setAttribute("myConnection",connection);
        } catch (SQLException e) {
            System.out.println("connection failed ----" + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("the application destroyed");

        try {
            ServletContext ctx = sce.getServletContext();
            Connection connection = (Connection) ctx.getAttribute("myConnection");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
