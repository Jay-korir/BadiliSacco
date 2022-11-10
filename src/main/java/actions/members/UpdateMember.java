package actions.members;


import bean.MembersBeanI;
import model.Members;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/updateMember")
public class UpdateMember extends HttpServlet {
    @EJB
    MembersBeanI membersBean;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ;
        resp.sendRedirect("./updateMember.jsp");
    }

    @SuppressWarnings("unchecked")
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Members members = new Members();
        try {
            BeanUtils.populate(members, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        if (StringUtils.isBlank(members.getFirstname())) {
            servletCtx.setAttribute("addError", "firstname is required");
            resp.sendRedirect("./updateMember.jsp");
            return;
        }

        if (StringUtils.isBlank(members.getLastname())) {
            servletCtx.setAttribute("addError", "lastname is required");
            resp.sendRedirect("./updateMember.jsp");
            return;
        }
        if (StringUtils.isBlank(members.getUsername())) {
            servletCtx.setAttribute("addError", "username is required");
            resp.sendRedirect("./updateMember.jsp");
            return;
        }
        if (StringUtils.isBlank(members.getEmail())) {
            servletCtx.setAttribute("addError", "email is required");
            resp.sendRedirect("./updateMember.jsp");
            return;
        }
        if (StringUtils.isBlank(members.getPhone())) {
            servletCtx.setAttribute("addError", " is required");
            resp.sendRedirect("./updateMember.jsp");
            return;
        }



        System.out.println(members);
        try {
            membersBean.add(members);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("./membersPage.jsp");
    }
}

