package bean;


import model.Members;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserBean implements UserBeanI {

    @PersistenceContext
    EntityManager em;

    public Members register(Members members) throws Exception {
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
        if(!(members.getPassword()==members.getConfirmPassword()))
            throw new Exception("password and confirm password");

        return em.merge(members);

    }

    public Members login(Members member) throws Exception {
        System.out.println("=======" + member.getUsername());
        System.out.println("=======" + member.getPassword());


        if (member.getUsername() == null || member.getPassword() == null)
            throw new Exception("Invalid password or username");

        List<Members> members = em.createQuery("FROM Members a WHERE a.username =:userName " +
                        "and a.password =:pwd", Members.class)
                .setParameter("userName", member.getUsername())
                .setParameter("pwd", member.getPassword())
                .getResultList();


        if (members == null || members.isEmpty() || members.get(0) == null)
            throw new Exception("Invalid username or password");

        return members.get(0);

    }

    public boolean authMd5(String md5Hash) {

        if (md5Hash == null)
            return false;

        List<Members> auths = em.createQuery("FROM Members m", Members.class)
                .getResultList();

        if (auths == null || auths.isEmpty())
            return false;

        boolean authenticated = false;
        for (Members members : auths) {
            if (DigestUtils.md5Hex(members.getUsername() + " SALT=CH10 " + members.getPassword()).equals(md5Hash)) {
                authenticated = true;
                break;
            }
        }

        return authenticated;

    }
}
