package actions;

import controller.UserBeanI;
import model.Members;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {

    @EJB
    UserBeanI userBean;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Members members = new Members();
        try {
            BeanUtils.populate(members, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            userBean.register(members);
            resp.sendRedirect("./login.jsp");
        } catch (Exception e) {
            servletCtx.setAttribute("registerError", e.getMessage());
            resp.sendRedirect("./register.jsp");
        }
    }
}
