package actions;



import controller.UserBeanI;
import model.Members;
import org.apache.commons.beanutils.BeanUtils;


import javax.ejb.EJB;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


@WebServlet(urlPatterns = "/login")
public class LogIn extends HttpServlet{

    @EJB
    UserBeanI userBean;

    ServletContext sCtx = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sCtx = config.getServletContext();

    }


    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
          Members member = new Members();
          member.setUsername("username");
          member.setPassword("password");

        try {
            BeanUtils.populate(member, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        try {
            Members members = userBean.login(member);
            HttpSession session = req.getSession(true);
            session.setAttribute("username",members.getUsername());
            session.setAttribute("loggedInTime", " Logged In At: " + new Date());


            System.out.println("===="+ members.getUserType());

            if ( members.getUserType().equals("admin"))
                res.sendRedirect("./dashboard.jsp");

            else if (members.getUserType().equals("user"))
                res.sendRedirect("./userDashboard.jsp");

        } catch (Exception e) {
            sCtx.setAttribute("loginError" , e.getMessage());
            res.sendRedirect("./login.jsp");
        }
       /* System.out.println("=================");
        System.out.println(userr);
        System.out.println(password);

        if (userr == null || userr.equalsIgnoreCase("")){
       sCtx.setAttribute("loginError","username is required");
        res.sendRedirect("./login.jsp");
            return;
    }
        if (password == null || password.equalsIgnoreCase("")) {
            sCtx.setAttribute("loginError","password is required");
            res.sendRedirect("./login.jsp");
            return;
            }

        User user = authBean.login(userr,  password);
          if (user == null) {
              sCtx.setAttribute("loginError","username & password is required");
              res.sendRedirect("./login.jsp");
               return;
            }

        HttpSession session = req.getSession(true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userType", user.getUserType());
        sCtx.setAttribute("username", user.getUsername());

        if (user.getUserType().equals("admin"))
            res.sendRedirect("./dashboard.jsp");
        else if (user.getUserType().equals("user"))
            res.sendRedirect("./userDashboard.jsp");

        */

    }


}
