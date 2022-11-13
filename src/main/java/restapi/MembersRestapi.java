package restapi;


import bean.ContributionBeanI;
import bean.MembersBeanI;
import model.Contribution;
import model.Members;
import rest.ResponseWrapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/members")
public class MembersRestapi {

    @EJB
    private MembersBeanI membersBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Members members){
        try {
            membersBean.add(members);
            return  Response.status(Response.Status.OK).entity(new ResponseWrapper()).build();
        } catch (Exception e) {
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseWrapper(false, e.getMessage())).build();
        }
    }

    @Path("/list/{id}/{badili}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("id") Long id,
                         @PathParam("badili")  String nameOfCompany) throws Exception {
        System.out.println("========The contribution id is " + id);
        System.out.println("======sacco name " + nameOfCompany);
        return Response.status(Response.Status.OK).entity(membersBean.list()).build();
    }
}
