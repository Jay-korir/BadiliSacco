package controller;

import model.Contribution;

import java.util.ArrayList;
import java.util.List;

public interface ContributionBeanI {
    void add(Contribution contribution);

    void update(Contribution contribution);

    void delete(Long contributionId);

    int totalUserContribution(String username);

    //List<Contribution> list(Contribution filter);
    List<Contribution> getList();

    Contribution getContribution(Long id);
}
