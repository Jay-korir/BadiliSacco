package service;

import model.Members;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/edit")
public class Edit extends HttpServlet {

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        List<Members> members = (List<Members>) session.getAttribute("members");
        int  memberId = Integer.parseInt((req.getParameter("id")));
        System.out.println("member id : " + memberId);
        for (Members member : members){
            if(member.getId() == memberId){
                resp.getWriter().print(this.editMember(null,member));
                break;
            }
        }

    }
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();

       //  req.getParameter()
        Members member = new Members();

        try {
            BeanUtils.populate(member, req.getParameterMap());
        } catch (Exception ex) {
            System.out.println("bean util error " + ex.getMessage());
        }
        if (StringUtils.isBlank(member.getFirstName())) {
            wr.print(this.editMember(" First Name is required<br/>",member));
            return;
        }
        if (StringUtils.isBlank(member.getLastName())){
            wr.print(this.editMember("lastName is required",member));
            return;
        }
        if (StringUtils.isBlank(member.getUserName())){
            wr.print(this.editMember("username is required",member));
            return;
        }
        if (StringUtils.isBlank(member.getEmail())){
            wr.print(this.editMember("email is required",member));
            return;
        }
        if (StringUtils.isBlank(member.getPhone())){
            wr.print(this.editMember("Phone number  is required",member));
            return;
        }

        HttpSession session = req.getSession();
        List<Members> members = (List<Members>) session.getAttribute("members");
        if(members == null)
            members = new ArrayList<>();

        members.add(member);
        session.setAttribute("members",members);
        session.removeAttribute(member.getEmail());

        String lName = (String) req.getAttribute("members");
        System.out.println(lName);


       // resp.sendRedirect("./welcome");
       RequestDispatcher dispatcher = req.getRequestDispatcher("welcome");
        dispatcher.forward(req,resp);
    }

    public String editMember(String actionError, Members member){
        return "<!DOCTYPE html>"
                + "<html> "
                + "<head> "
                + "</head>"
                + "<body>"
                // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<h2> Edit Member</h2>"
                + "<form action=\"./edit\" method=\"post\">"
                + "<table> "
                + "<tr> <td> First Name: </td> <td> <input type=\"text\" value= "+member.getFirstName() + ""
                + "> </td> </tr> "
                + "<tr> <td> Last name: </td> <td> <input type=\"text\" value=" +member.getLastName() +""
                +"> </td> </tr> "
                + "<tr> <td> User Name: </td> <td> <input type=\"text\" value="+member.getUserName() + ""
                + "> </td> </tr> "
                + "<tr> <td> Email: </td> <td> <input type=\"text\" value=" +member.getEmail() +""
                +"> </td> </tr> "
                + "<tr> <td> Phone: </td> <td> <input type=\"text\" value=" +member.getPhone() +""
                +"> </td> </tr> "
                + "<tr> <td> <input type=\"submit\" value=\"Submit\"></tr> "
                + "</table>"
                + "</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span><br/>"
                + "Home? <a href='./welcome'>Back</a><br/>"
                + "</body>"
                + "</html>";
    }
}
