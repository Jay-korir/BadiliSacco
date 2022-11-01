package model;

import javax.inject.Inject;
import javax.inject.Named;

@Named("contributionI")
public class Contribution extends BaseEntity{
    static  int maxId = 0;
    private String username;
    private String month;
    private   double amount;

    private  int id;

    @Inject
    public Contribution() {
        maxId = maxId + 1;
        id = maxId;

    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
