package controller;

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

@WebServlet("/contribution")
public class Contribution extends HttpServlet {
    static  String url;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        HttpSession session = req.getSession();
        members = (List<Members>) session.getAttribute("members");
        //int  memberId = Integer.parseInt((req.getParameter("id")));
       // System.out.println("member id : " + memberId);
        for (Members member : members){
            if(member.getId() == member.getId()){
               // resp.getWriter().print(this.getMember(null,member));
                resp.getWriter().print(this.addContribution(null,member));
                break;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();


        Members member = new Members();

        try {
            BeanUtils.populate(member, req.getParameterMap());
        } catch (Exception ex) {
            System.out.println("bean util error " + ex.getMessage());
        }
        if (StringUtils.isBlank(member.getMonth())) {
            wr.print(this.addContribution(" month required is required<br/>",member));
            return;
        }
        if (StringUtils.isBlank(member.getAmount())){
            wr.print(this.addContribution("amount  is required",member));
            return;
        }

        HttpSession session = req.getSession();
        url = session.getId();
        members = (List<Members>) session.getAttribute("members");
        if(members == null)
            members = new ArrayList<>();

        members.add(member);
        session.setAttribute("members",members);

        RequestDispatcher dispatcher = req.getRequestDispatcher("display");
        dispatcher.forward(req,resp);
    }

    public String addContribution(String actionError, Members member){
        return "<!DOCTYPE html>"
                + "<html> "
                + "<head> "
                + "</head>"
                + "<body bgcolor=\"Lightskyblue\"  >"
                // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<h2>Contribution</h2>"
                + "<form action=\"./contribution\" method=\"post\">"
                + "<table> "
                + "<tr> <td> User Name: </td> <td> <input type=\"text\"name= \"userName\" value="+member.getUserName() + ""
                + "> </td> </tr> "
                + "<tr> <td> Email: </td> <td> <input type=\"text\"name= \"email\" value=" +member.getEmail() +""

                + "> </td> </tr> "
                + "<tr> <td> Month: </td> <td> <input type=\"text\" name=\"month\"> </td> </tr> "
                + "<tr> <td> Amount: </td> <td> <input type=\"number\" name=\"amount\"> </td> </tr> "

                + "<tr> <td> <input type=\"submit\" value=\"Submit\"></tr> "
                + "</table>"
                + "</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span><br/>"
                + "Home? <a href='./welcome'>Back</a><br/>"
                + "</body>"
                + "</html>";
    }
    public String getMember(String actionError, Members member){
        return "<!DOCTYPE html>"
                + "<html> "
                + "<head> "
                + "</head>"
                + "<body>"
                // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<h2> Edit Member</h2>"
                + "<form action=\"./edit?id="+ member.getId()+ "\" method=\"post\">"
                + "<table> "

                + "<tr> <td> User Name: </td> <td> <input type=\"text\"name= \"userName\" value="+member.getUserName() + ""
                + "> </td> </tr> "
                + "<tr> <td> Email: </td> <td> <input type=\"text\"name= \"email\" value=" +member.getEmail() +""

                + "<tr> <td> <input type=\"submit\" value=\"Submit\"></tr> "
                + "</table>"
                + "</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span><br/>"
                + "Home? <a href='./welcome'>Back</a><br/>"
                + "</body>"
                + "</html>";
    }
}


