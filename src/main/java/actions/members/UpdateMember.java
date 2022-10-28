package actions.members;


import controller.MembersController;
 import model.Members;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/updateMember")
public class UpdateMember extends HttpServlet {
    @Inject
    MembersController membersController;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();;
        resp.sendRedirect("./updateMember.jsp");
    }

    @SuppressWarnings("unchecked")
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        Members members = new Members();
        try {
            BeanUtils.populate(members, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        if (StringUtils.isBlank(members.getFirstName())) {
            servletCtx.setAttribute("addError","firstname is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }

        if (StringUtils.isBlank(members.getLastName())) {
            servletCtx.setAttribute("addError","lastname is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }
        if (StringUtils.isBlank(members.getUserName())) {
            servletCtx.setAttribute("addError","username is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }
        if (StringUtils.isBlank(members.getEmail())) {
            servletCtx.setAttribute("addError","email is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }
        if (StringUtils.isBlank(members.getPhone())) {
            servletCtx.setAttribute("addError"," is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }


        members.setUserName("firstName");
        members.setUserName("lastName");
        members.setUserName("userName");
        members.setUserName("email");
        members.setUserName("phone");


        membersController.update(members);

        resp.sendRedirect("./membersPage.jsp");
    }
}

