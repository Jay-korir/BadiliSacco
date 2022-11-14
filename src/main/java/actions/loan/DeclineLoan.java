package actions.loan;


import bean.LoanBeanI;
import model.Loan;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/decline")
public class DeclineLoan extends HttpServlet {
    @EJB
    LoanBeanI loanBean;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        ;
//        resp.sendRedirect("./loanApprove.jsp");
//    }

    @SuppressWarnings("unchecked")
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        Loan loan = new Loan();
        try {
            BeanUtils.populate(loan, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

 loan.setStatus("pending");

        try {
            loanBean.update(loan);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("./loanPage.jsp");
    }
}


