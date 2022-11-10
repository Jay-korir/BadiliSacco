package view;

import controller.ContributionBeanI;
import model.Contribution;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("contributionView")
public class ContributionView implements Serializable {
    @EJB
  private   ContributionBeanI contributionBean;

    public List<Contribution> getList(){
        return contributionBean.list();

    }
}
