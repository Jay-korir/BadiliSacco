package actions.loan;

import bean.ContributionBeanI;
import bean.LoanBeanI;
import model.Contribution;
import model.Loan;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateLoan")
public class UpdateLoan extends HttpServlet {
    @EJB
    LoanBeanI loanBean;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }


    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Loan loan = new Loan();
        System.out.println("===================");
        try {
            BeanUtils.populate(loan, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(loan);


        try {
            loanBean.update(loan);
            resp.sendRedirect("./loanPage.jsp");
        } catch (Exception e) {
            servletCtx.setAttribute("addError", e.getMessage());
        }


    }

}
