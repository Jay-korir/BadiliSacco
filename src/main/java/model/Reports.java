package model;

import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class Reports extends BaseEntity{

    @Formula("(select username(m.username) from Members m)")
    private Members members;

    private Contribution contribution;

    private String username;

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public Contribution getContribution() {
        return contribution;
    }

    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
