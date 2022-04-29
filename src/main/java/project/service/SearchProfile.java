package project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.entity.User;
import project.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/profile")
public class SearchProfile {
    GenericDao genericDaoUser;
    Logger logger = LogManager.getLogger(this.getClass());

    public SearchProfile() {
        genericDaoUser = new GenericDao(User.class);
    }

    @GET
    @Path("/{param}")
    @Produces("text/html")
    public void getProfile(@Context HttpServletResponse resp,
                         @Context HttpServletRequest req,
                         @PathParam("param") String userName) throws ServletException, IOException {
        boolean obtainedUser = false;
        String url = null;
        try {
            List<User> userList = genericDaoUser.getByPropertyEqual("userName", userName);
            User userProfile = userList.get(0);
            req.setAttribute("userProfile", userProfile);
            obtainedUser = true;
        } catch (Exception e){
            logger.error("Exception error while searching for user: ", e);
        }
        if (obtainedUser) {
            url = "userprofile.jsp";
        } else {
            url = "error.jsp";
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
