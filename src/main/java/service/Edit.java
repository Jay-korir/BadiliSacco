package service;

import controller.WelcomeServlet;
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

import static controller.WelcomeServlet.members;


@WebServlet("/edit")
public class Edit extends HttpServlet {

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();
        HttpSession session = req.getSession();
        members = (List<Members>) session.getAttribute("members");
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
         String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        String id = req.getParameter("id");


        HttpSession session = req.getSession();
        members = (List<Members>) session.getAttribute("members");

        for (Members member : members) {
            if (member.getId() == Integer.parseInt(id)) {
                member.setFirstName(firstName);
                member.setLastName(lastName);
                member.setUserName(userName);
                member.setEmail(email);
                member.setPhone(phone);
                break;
            }
        }
        resp.sendRedirect("./welcome");

    }

    public String editMember(String actionError, Members member){
        return "<!DOCTYPE html>"
                + "<html> "
                + "<head> "
                + "</head>"
                + "<body>"
                // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<h2> Edit Member</h2>"
                + "<form action=\"./edit?id="+ member.getId()+ "\" method=\"post\">"
                + "<table> "
                + "<tr> <td> First Name: </td> <td> <input type=\"text\" name= \"firstName\" value= "+member.getFirstName() + ""
                + "> </td> </tr> "
                + "<tr> <td> Last name: </td> <td> <input type=\"text\" name= \"lastName\" value=" +member.getLastName() +""
                +"> </td> </tr> "
                + "<tr> <td> User Name: </td> <td> <input type=\"text\"name= \"userName\" value="+member.getUserName() + ""
                + "> </td> </tr> "
                + "<tr> <td> Email: </td> <td> <input type=\"text\"name= \"email\" value=" +member.getEmail() +""
                +"> </td> </tr> "
                + "<tr> <td> Phone: </td> <td> <input type=\"text\"name= \"phone\" value=" +member.getPhone() +""
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
