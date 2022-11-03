package actions.contribution;

import controller.ContributionController;
import controller.Validator;
import model.Contribution;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
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

@WebServlet("/contribution")
public class ContributionAction extends HttpServlet {

    private Validator validator;
    //injecting into a constructor done here
    @Inject
   public ContributionAction(Validator validator) {
       this.validator = validator;
   }
   //injecting this method done here
    @Inject
    public void setValidator(Validator validator){
        this.validator = validator;
    }
    @Inject
    ContributionController contributionController;

    @Inject
    Contribution contribution;
    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


         contribution = new Contribution();
        try {
            BeanUtils.populate(contribution, req.getParameterMap());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        if (StringUtils.isBlank(contribution.getUsername())) {
            servletCtx.setAttribute("addError", "username is required");
            res.sendRedirect("./addContribution.jsp");
            return;
        }

        if (StringUtils.isBlank(contribution.getMonth())) {
            servletCtx.setAttribute("addError", "month is required");
            res.sendRedirect("./addContribution.jsp");
            return;
        }
        if (contribution.getAmount() == 0) {
            servletCtx.setAttribute("addError", "amount is required");
            res.sendRedirect("./addContribution.jsp");
            return;
        }


       // contributionController.add(contribution);
        this.add(contribution);
        System.out.println("==========from constructor");
        System.out.println(validator.validateContribution((int) contribution.getAmount()));
           this.setValidator(validator);
        System.out.println("=========from the set method");
        System.out.println(validator.validateContribution((int) contribution.getAmount()));
        res.sendRedirect("./contributionPage.jsp");

        //the output i got
        /*
        17:07:05,444 INFO  [stdout] (default task-3) ==========from constructor
17:07:05,444 INFO  [stdout] (default task-3)
17:07:05,446 INFO  [stdout] (default task-3) ****valid
17:07:05,447 INFO  [stdout] (default task-3) =========from the set method
17:07:05,450 INFO  [stdout] (default task-3)
17:07:05,451 INFO  [stdout] (default task-3) ****valid
         */


    }
    @Inject
    public void add(Contribution contribution){

        ContributionController contributionController1  = contributionController;
        contributionController1.add(contribution);
    }

}

