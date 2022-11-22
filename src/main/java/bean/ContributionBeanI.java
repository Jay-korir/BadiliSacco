package bean;

import model.Contribution;

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

    List<Contribution> contributionReport(String type);

    List<Contribution> userContribution(String username);

    double payUserLoan(String username);

    double payPenalty(String username);

    void groupBy();

    void orderBy();

    void limit();

    void inner();

    void leftJoin();

    void crossJoin();

    double payLoan();

    double payPenalty();
}
