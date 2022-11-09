package actions.members;

import controller.MembersBean;
import controller.MembersBeanI;
import model.Members;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/add")
public class MembersAction extends HttpServlet {

    @EJB
    MembersBeanI membersBean;

    ServletContext servletCtx = null;



    public void init(ServletConfig config) throws ServletException {
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


        try {
            membersBean.add(member);
            resp.sendRedirect("./membersPage.jsp");
        } catch (Exception e) {
            servletCtx.setAttribute("addError",e.getMessage());
            resp.sendRedirect("./addMembers.jsp");
        }

    }

}
