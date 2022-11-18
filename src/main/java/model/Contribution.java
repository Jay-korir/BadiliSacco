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

    @Column
    private  String type;

    @Column(name = "id_number")
    private  Long idNumber;




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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "Contribution{" +
                "id='" + getId() + '\'' +
                "username='" + username + '\'' +
                ", month='" + month + '\'' +
                ", type='" + type+ '\'' +
                ", amount=" + amount +
                '}';
    }
}
