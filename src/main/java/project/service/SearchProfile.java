package project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.mvc.Viewable;
import project.entity.User;
import project.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/profile")
public class SearchProfile {
    GenericDao genericDaoUser;
    Logger logger = LogManager.getLogger(this.getClass());

    public SearchProfile() {
        genericDaoUser = new GenericDao(User.class);
    }

    @GET
    @Path("/{param}")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getProfile(@PathParam("param") String userName) {
        return new Viewable("/userprofile");
    }
}
