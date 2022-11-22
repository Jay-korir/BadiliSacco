package soap;


import bean.ContributionBeanI;
import model.Contribution;
import rest.ResponseWrapper;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.*;


import javax.ws.rs.core.Response;

@WebService
public class ContributionSoap {

    @EJB
    private ContributionBeanI contributionBean;

    @Path("/add")
    @POST
    public Response add(Contribution contribution) {
        try {
            contributionBean.add(contribution);
            return Response.status(Response.Status.OK).entity(new ResponseWrapper()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseWrapper(false, e.getMessage())).build();
        }
    }

    @Path("/list")
    @GET
    public Response list() {

        System.out.println("==============");
        System.out.println(contributionBean.list());
        return Response.status(Response.Status.OK).entity(contributionBean.list()).build();
    }
}

