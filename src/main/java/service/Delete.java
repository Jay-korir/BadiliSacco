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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



    @WebServlet("/delete")
    public class Delete  extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
PrintWriter pr = resp.getWriter();
Members members = new Members();
HttpSession session = req.getSession();
if (session.isNew()){
session.removeAttribute("members");
}
RequestDispatcher dispatcher = req.getRequestDispatcher("welcome");
dispatcher.forward(req,resp);
        }


    }




