package controller;

import model.Contribution;

import java.util.ArrayList;
import java.util.List;

public interface ContributionBeanI {
    void add(Contribution contribution) throws Exception;

    void update(Contribution contribution) throws Exception;

    void delete(Long contributionId);


    List<Contribution> list();

    Contribution getContribution(Long id);

    double getTotalContribution();

    double totalUserContribution(String username);

    List<Contribution> getListUser(String username);
}
