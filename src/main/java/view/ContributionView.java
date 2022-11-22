package view;

import bean.ContributionBeanI;
import model.Contribution;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static actions.LogIn.loggedUser;

@Named("contributionView")
public class ContributionView implements Serializable {
    @EJB
    private ContributionBeanI contributionBean;

    public List<Contribution> getList() {
        return contributionBean.list();

    }

    public List<Contribution> getContributionReport(String type) {
        return contributionBean.contributionReport(type);

    }

    public double getMyContribution(String username) {
        return contributionBean.totalUserContribution(username);
    }

    public double getTotalContri() {
        return contributionBean.getTotalContribution();
    }

    public Contribution getMyContribution(Long id) {
        return contributionBean.getContribution(id);
    }

    public List<Contribution> getUserContribution(String username) {
        return contributionBean.getListUser(username);

    }

    public double getPenalty() {

        return contributionBean.payPenalty();

    }

    public double getPaidLoan() {
        return contributionBean.payLoan();
    }


    public double getUserPaidLoan(String username) {
        return contributionBean.payUserLoan(username);
    }
}