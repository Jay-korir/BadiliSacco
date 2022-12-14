package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "loans")
public class Loan extends BaseEntity {
    @Column
    private String username;

    @Column
    private double loanAmount;

    @Column
    private int period;

    @Column
    private String purpose;

    @Column
    private double interest = (2 / 100);

    @Column
    private double totalPay;

    @Column
    private String status;

    @Column
    private double userContribution;

    @Column(name = "id_number")
    private Long idNumber;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public double getInterest() {
        return interest;
    }

    public double getTotalPay() {

        return totalPay;
    }

    public double getUserContribution() {
        return userContribution;
    }

    public void setUserContribution(double userContribution) {
        this.userContribution = userContribution;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "username='" + username + '\'' +
                ", loanAmount=" + loanAmount +
                ", period=" + period +
                ", purpose='" + purpose + '\'' +
                ", interest=" + interest +
                ", totalPay=" + totalPay +
                ", status='" + status + '\'' +
                ", userContribution=" + userContribution +
                '}';
    }
}
