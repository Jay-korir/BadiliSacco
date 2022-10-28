package actions.members;

import controller.ContributionController;
import controller.MembersController;
import model.Members;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;


@WebServlet("/add")
public class MembersAction extends HttpServlet {

    @Inject
    MembersController membersController;
    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }


    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Members member = new Members();

        try {
            BeanUtils.populate(member, req.getParameterMap());
        } catch (Exception ex) {
            System.out.println("bean util error " + ex.getMessage());
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(member.getFirstName())) {
            servletCtx.setAttribute("addError","firstname is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }
        if (StringUtils.isBlank(member.getLastName())){
            servletCtx.setAttribute("addError","lastname is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }
        if (StringUtils.isBlank(member.getUserName())){
            servletCtx.setAttribute("addError","username is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }
        if (StringUtils.isBlank(member.getEmail())){
            servletCtx.setAttribute("addError","Email is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }
        if (StringUtils.isBlank(member.getPhone())){
            servletCtx.setAttribute("addError","phone is required");
            resp.sendRedirect("./addMembers.jsp");
            return;
        }


        membersController.add(member);
        resp.sendRedirect("./membersPage.jsp");
    }

}
