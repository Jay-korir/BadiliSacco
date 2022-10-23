package eventListeners;

import com.mchange.v2.c3p0.ComboPooledDataSource;
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
        servletContext.setAttribute("applicationLabel","BADILI SACCO");



        try {
            System.out.println("connections starting -----");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sacco","root","");

           // MysqlDataSource dataSource = new MysqlDataSource();
          //  BasicDataSource dataSource = new BasicDataSource();
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/sacco");
            dataSource.setUser("root");
            dataSource.setPassword("");

            dataSource.setInitialPoolSize(2);
            dataSource.setMaxIdleTime(2);
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
