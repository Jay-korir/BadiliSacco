package bean;

import model.Members;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class MembersBean implements MembersBeanI {


    @PersistenceContext
    EntityManager em;

    public void add(Members members) throws Exception {
        if (members == null || StringUtils.isBlank(members.getFirstname()) || StringUtils.isBlank(members.getLastname()) ||
                StringUtils.isBlank(members.getUsername()) || StringUtils.isBlank(members.getEmail()) || StringUtils.isBlank(members.getPhone()))
            throw new Exception(" all fields  required not be null");

        em.merge(members);

    }


    public void update(Members members) {

    }

    public void delete(Long memberId) {
        System.out.println(this.getMember(memberId));
        em.remove(em.find(Members.class, memberId));

    }

    public Members getMember(Long id) {
        return em.createQuery("FROM Members s WHERE s.id =:Id", Members.class)
                .setParameter("Id", id)
                .getResultList().get(0);
    }

    public Members getMember(int id) {
        Members members = new Members();


        return members;
    }

    public List<Members> list() {

        return em.createQuery("FROM Members s", Members.class).getResultList();
    }


}
