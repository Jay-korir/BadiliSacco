package restapi;

import bean.UserBeanI;
import model.Members;
import rest.ResponseWrapper;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthApi {
    @EJB
    UserBeanI userBean;

    @PermitAll
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(RestLoginWrapper loginWrapper) {

        Members members = new Members();
        members.setUsername(loginWrapper.getUsername());
        members.setPassword(loginWrapper.getPassword());
        try {
            Members members1 = userBean.login(members);
            return Response.status(Response.Status.OK)
                    .entity(new ResponseWrapper("authorized", members1.getBearerToken())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseWrapper(false, e.getMessage())).build();
        }


    }
}
