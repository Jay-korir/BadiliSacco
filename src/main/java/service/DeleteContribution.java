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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@WebServlet("/deleteContribution")

public class DeleteContribution extends HttpServlet {

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }
    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int myId = Integer.parseInt(req.getParameter("id"));
        this.delete(myId);
        RequestDispatcher dispatcher = req.getRequestDispatcher("control");
        dispatcher.forward(req, res);
    }

    public int delete(int id){
        int status=0;
        try{
            Connection connection = (Connection) servletCtx.getAttribute("myConnection");
            Statement sqlStmt = connection.createStatement();

            ResultSet result = sqlStmt.executeQuery("delete from contribution where " + id);


        }catch(Exception e1){e1.printStackTrace();}

        return status;
    }

}