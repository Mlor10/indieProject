package project.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/profile")
public class SearchProfile {

    public SearchProfile() {
    }

    @GET
    @Path("/{param}")
    @Produces("application/json")
    public Response getCard(@PathParam("param") String userName) {
        String searchType = "userName=";

        String output = searchType + userName;
        if(output == null){
            output = "Error!";
            return Response.status(404).entity(output).build();
        } else {
            return Response.status(200).entity(output).build();
        }
    }
}
