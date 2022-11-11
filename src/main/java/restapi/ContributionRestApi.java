package restapi;


import bean.ContributionBeanI;
import model.Contribution;
import rest.ResponseWrapper;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/contribution")
public class ContributionRestApi {

    @EJB
    private ContributionBeanI contributionBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Contribution contribution){
        try {
            contributionBean.add(contribution);
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
                         @PathParam("badili")  String nameOfCompany) {
        System.out.println("========The contribution id is " + id);
        System.out.println("======sacco name " + nameOfCompany);
        return Response.status(Response.Status.OK).entity(contributionBean.list()).build();
    }
}
