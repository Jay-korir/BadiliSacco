package model;

public class Loan  extends  BaseEntity{
   private  String username;
    private  double loanAmount;
    private  int period;
    private String purpose;

    private  double interest = (2/100);

    private double totalpay;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setTotalpay(double totalpay) {
        this.totalpay = totalpay;
    }

    public double getInterest() {
        return interest;
    }

    public double getTotalpay() {
        return (interest * loanAmount * period) + loanAmount;
    }
}
