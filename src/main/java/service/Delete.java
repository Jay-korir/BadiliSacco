package service;

import controller.SaccoMember;
import controller.WelcomeServlet;
import eventListeners.SessionListener;
import model.Members;
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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/delete")
    public class Delete  extends HttpServlet {

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         int  memberId = Integer.parseInt((req.getParameter("id")));
       System.out.println("member id : " + memberId);
        String name = req.getParameter("name");
        System.out.println("my name : " + name);

        HttpSession session = req.getSession();
        List<Members> member = (List<Members>) session.getAttribute("members");
       // for (Members members : member) {
         //   if (members.getFirstName().equalsIgnoreCase(name)) {
           //     member.remove(members);
             //   break;
           // }
       // }
        for (Members members : member){
            if(members.getId() == memberId){
            member.remove(members);
            break;
            }
        }
            resp.sendRedirect("./welcome");




    }
}




