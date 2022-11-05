package controller;

import model.Members;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Remote
@Named("memberController")
public class MembersBean implements MembersBeanI {


    @PersistenceContext
    EntityManager em;

    public void add(Members members){
        if ( members == null ||StringUtils.isBlank(members.getFirstname()) || StringUtils.isBlank(members.getLastname()) ||
                StringUtils.isBlank(members.getUsername())|| StringUtils.isBlank(members.getEmail())||StringUtils.isBlank(members.getPhone()))
            return;

        em.merge(members);

    }


    public List<Members> list(Members filter){
        List<Members> members = new ArrayList<Members>();


        return members;
    }

    public void update(Members members) {

    }

    public void delete(Members members) {

    }
    public Members getMember(int id) {
         Members members = new Members();


        return members;
    }

    public List<Members> getList() {

        return em.createQuery("FROM Members s", Members.class).getResultList();
    }


}
