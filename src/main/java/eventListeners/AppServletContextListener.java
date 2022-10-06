package eventListeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("start of initialization ");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("applicationlabel","BADILI SACCO");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("the application destroyed");

    }
}
