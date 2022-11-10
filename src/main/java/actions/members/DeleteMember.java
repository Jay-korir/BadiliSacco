package actions.members;


import bean.MembersBeanI;
import model.Members;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteMember")
public class DeleteMember extends HttpServlet {
    @EJB
    MembersBeanI membersBean;

    static ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }
    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Members members = new Members();
        members.setId(Long.valueOf(Integer.parseInt(req.getParameter("id"))));


        try {
            membersBean.delete(members.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        res.sendRedirect("./membersPage.jsp");
    }



}
