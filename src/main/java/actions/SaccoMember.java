package actions;

import model.Members;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;


@WebServlet("/add")
public class SaccoMember extends HttpServlet {
    static  String url;
    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pr = resp.getWriter();
        pr.print(this.addMember(null));


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
        if (org.apache.commons.lang3.StringUtils.isBlank(member.getFirstName())) {
            wr.print(this.addMember(" First Name is required<br/>"));
            return;
        }
        if (StringUtils.isBlank(member.getLastName())){
            wr.print(this.addMember("lastName is required"));
            return;
        }
        if (StringUtils.isBlank(member.getUserName())){
            wr.print(this.addMember("username is requuired"));
            return;
        }
        if (StringUtils.isBlank(member.getEmail())){
            wr.print(this.addMember("email is requuired"));
            return;
        }
        if (StringUtils.isBlank(member.getPhone())){
            wr.print(this.addMember("Phone number  is requuired"));
            return;
        }

       this.insert(member);

    RequestDispatcher dispatcher = req.getRequestDispatcher("welcome");
    dispatcher.forward(req,resp);
    }

    public String addMember(String actionError){
        return "<!DOCTYPE html>"
                + "<html> "
                + "<head> "
                + "</head>"
                + "<body bgcolor=\"Lightskyblue\" >"
               // + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<h2>Members</h2>"
                + "<form action=\"./add\" method=\"post\">"
                + "<table> "
                + "<tr> <td> First Name: </td> <td> <input type=\"text\" name=\"firstName\"> </td> </tr> "
                + "<tr> <td> Last name: </td> <td> <input type=\"text\" name=\"lastName\"> </td> </tr> "
                + "<tr> <td> User Name: </td> <td> <input type=\"text\" name=\"userName\"> </td> </tr> "
                + "<tr> <td> Email: </td> <td> <input type=\"text\" name=\"email\"> </td> </tr> "
                + "<tr> <td> Phone: </td> <td> <input type=\"password\" name=\"phone\"> </td> </tr> "
                + "<tr> <td> <input type=\"submit\" value=\"Add\"></tr> "
                + "</table>"
                + "</form>"
                + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span><br/>"
                + "Home? <a href='./home'>Register</a><br/>"
                + "</body>"
                + "</html>";
    }

    public  void  insert(Members members){
        if ( members == null ||StringUtils.isBlank(members.getFirstName()) || StringUtils.isBlank(members.getLastName()) ||
        StringUtils.isBlank(members.getUserName())|| StringUtils.isBlank(members.getEmail())||StringUtils.isBlank(members.getPhone()))
             return;



        try  {
        Connection connection = (Connection) servletCtx.getAttribute("myConnection");
            Statement statement = connection.createStatement();
            statement.execute("insert into members(firstname,lastname,username,email,phone)" +
                    "values('"+ members.getFirstName() + "', '"+members.getLastName() + "','"+members.getUserName() + "','"+members.getEmail() + "','"+members.getPhone() + "')");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
