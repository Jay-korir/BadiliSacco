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



    @WebServlet("/delete")
    public class Delete  extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            PrintWriter pr = resp.getWriter();



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
             Members mb = new Members();

            HttpSession session = req.getSession();


            RequestDispatcher dispatcher = req.getRequestDispatcher("welcome");
            dispatcher.forward(req,resp);
        }
        public  String membersList(List<Members> mb){
            if(mb == null)
                mb = new ArrayList<Members>();
            String membersList = "<table >" +
                    "<tr>" +
                    "<th>FirstName</th>" +
                    "<th>LastName</th>" +
                    "<th>username</th>" +
                    "<th>Email</th>" +
                    "<th>Phone Number</th>" +
                    "</tr>";
            for (Members member : mb)
                membersList += "<tr>"
                        + "<td>" + member.getFirstName() + "</td>"
                        + "<td>" + member.getLastName() + "</td>"
                        + "<td>" + member.getUserName() + "</td>"
                        + "<td>" + member.getEmail() + "</td>"
                        + "<td>" + member.getPhone() + "</td>"
                        + "<td><a href=\"./edit\">Edit</a>  | <a href=\"./delete\">Delete</a></td>"
                        + "</tr>";

            membersList += "</table>";
            return membersList;
        }

    }


