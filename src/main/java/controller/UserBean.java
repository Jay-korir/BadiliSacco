package controller;


import model.Members;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class UserBean implements UserBeanI{

    @PersistenceContext
    EntityManager em;

    public Members register(Members members) throws Exception{
        if (members == null)
            throw new Exception("Invalid details");

        if (members.getFirstname() == null)
            throw new Exception("firstname is required");
        if (members.getLastname() == null)
            throw new Exception("lastname is required");

        if (members.getEmail() == null)
            throw new Exception("Email is required");

        if (members.getPhone() == null)
            throw new Exception("phone is required");

        return em.merge(members);

    }

    public Members login(Members member) throws Exception {
        System.out.println("=======" + member.getUsername());
        System.out.println("=======" + member.getPassword());


        if (member.getUsername() == null || member.getPassword() == null)
            throw  new Exception("Invalid password or username");

        List<Members> members = em.createQuery("FROM Members a WHERE a.username =:userName " +
                        "and a.password =:pwd", Members.class)
                .setParameter("userName", member.getUsername())
                .setParameter("pwd", member.getPassword())
                .getResultList();


        if(members == null || members.isEmpty() || members.get(0) == null)
            throw new Exception("Invalid username or password");

        return members.get(0);

    }
}
