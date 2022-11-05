package controller;

import model.Contribution;

import java.util.List;

public interface ContributionBeanI {
    void  add(Contribution contribution);
     void update(Contribution contribution);
    void  delete(Contribution contribution);
    int totalUserContribution(String username);
    List<Contribution> list(Contribution filter);
    List<Contribution> getList();
}
