package model;


import javax.persistence.*;

@Entity
@Table(name = "contributions")
public class Contribution extends BaseEntity {
    @Column
    private String username;

    @Column
    private String month;

    @Column
    private double amount;




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

    @Override
    public String toString() {
        return "Contribution{" +
                "id='" + getId() + '\'' +
                "username='" + username + '\'' +
                ", month='" + month + '\'' +
                ", amount=" + amount +
                '}';
    }
}
