package service;


import model.Contribution;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/deleteContribution")

public class DeleteContribution extends HttpServlet {

    static ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }
    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       // int myId = Integer.parseInt(req.getParameter("id"));
       // System.out.println(myId);
       // this.delete(myId);
        Contribution contribution = new Contribution();
        String id = contribution.getUsername();
        System.out.println("============id =======" + id);
        this.delete(id);
         res.sendRedirect("./contributionPage.jsp");
    }


    public static int delete(String username){

        int status=0;
        try{
            Connection connection = (Connection) servletCtx.getAttribute("myConnection");
            PreparedStatement ps=connection.prepareStatement("delete from contribution where username=?");
            ps.setString(1,username);
            status=ps.executeUpdate();

            connection.close();
        }catch(Exception e1){e1.printStackTrace();}

        return status;
    }

}