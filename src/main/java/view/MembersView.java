package view;

import bean.MembersBeanI;
import model.Members;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("memberView")
public class MembersView implements Serializable {

    @EJB
    private MembersBeanI membersBean;

    public List<Members> getList() throws Exception {
        return membersBean.list();
    }

    public Members editMember(Long id) throws Exception {
        return membersBean.getMember(id);
    }
}
