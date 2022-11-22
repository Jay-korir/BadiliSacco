package restapi;

import bean.UserBeanI;
import model.Members;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService
public class AuthSoapApi {
    @EJB
    UserBeanI userBean;

    public Members login(String username, String password) {
        return new Members(); //authBean.login(new Auth());
    }


}
