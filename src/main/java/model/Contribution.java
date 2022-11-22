package model;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contribution")
@NamedQueries({
        @NamedQuery(name = Contribution.FIND_ALL, query = "SELECT c FROM Contribution c"),
        @NamedQuery(name = Contribution.FIND_WITH_ID, query = "SELECT c FROM Contribution c WHERE c.id=:contributionId"),
        @NamedQuery(name = Contribution.FIND_WITH_USERNAME, query = "SELECT c FROM Contribution c WHERE c.username=:userName")
})


@Entity
@Table(name = "contributions")
public class Contribution extends BaseEntity {


    public static final String FIND_ALL = "Contribution.findAll";
    public static final String FIND_WITH_ID = "Contribution.FIND_WITH_ID";
    public static final String FIND_WITH_USERNAME = "Contribution.FIND_WITH_USERNAME";

    @Column
    private String username;

    @Column
    private String month;

    @Column
    private double amount;

    @Column
    private String type;

    @Column(name = "id_number")
    private Long idNumber;

    public Contribution() {
    }

    public Contribution(String username, String month, double amount, String type, Long idNumber, double total) {
        this.username = username;
        this.month = month;
        this.amount = amount;
        this.type = type;
        this.idNumber = idNumber;
        this.total = total;
    }

    @Transient
    private double total;


    public Contribution(double total) {
        this.total = total;
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
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
