package actions;

import bean.ContributionBeanI;
import bean.LoanBeanI;
import bean.UserBeanI;
import model.Contribution;
import model.Loan;
import model.Members;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static actions.LogIn.loggedUser;

@WebServlet("/register")
public class Register extends HttpServlet {

    @EJB
    UserBeanI userBean;

    @EJB
    ContributionBeanI contributionBean;

    @EJB
    LoanBeanI loanBean;

    ServletContext servletCtx = null;

    Contribution contribution = new Contribution();

    Loan loan = new Loan();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Members members = new Members();
        try {
            BeanUtils.populate(members, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        members.setPassword(DigestUtils.md5Hex(req.getParameter("password")));
        members.setConfirmPassword(DigestUtils.md5Hex(members.getPassword()));


        contribution.setUsername(members.getUsername());
        contribution.setMonth("January");
        contribution.setAmount(10);
        contribution.setIdNumber(members.getIdNumber());
        contribution.setType("Daily/monthly");
        System.out.println(contribution);
        loan.setUsername(members.getUsername());
        loan.setUserContribution(contribution.getAmount());
        loan.setLoanAmount(10);
        loan.setPeriod(1);
        loan.setPurpose("Others");
        loggedUser = "admin";
        System.out.println(loan);

        try {
            userBean.register(members);
            contributionBean.add(contribution);
            loanBean.add(loan);
            resp.sendRedirect("./login.jsp");
        } catch (Exception e) {
            servletCtx.setAttribute("registerError", e.getMessage());
            resp.sendRedirect("./register.jsp");
        }
    }
}
