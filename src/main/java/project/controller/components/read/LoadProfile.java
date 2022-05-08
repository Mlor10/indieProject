package project.controller.components.read;

import project.entity.User;
import project.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet that loads the user profile and forwards to the userprofile page
 */
@WebServlet(
        urlPatterns = {"/profile"}
)
public class LoadProfile extends HttpServlet {

    /**
     * Handles HTTP GET requests
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao genericDaoUser = new GenericDao(User.class);
        String userName = req.getParameter("userName");
        String url;
        List<User> searchUser = genericDaoUser.getByPropertyEqual("userName", userName);
        if (!searchUser.isEmpty()) {
            User userProfile = searchUser.get(0);
            req.setAttribute("userProfile", userProfile);
            url = "userprofile.jsp";
        } else {
            url = "error.jsp";
            req.setAttribute("errorMessage", "ERROR: could not load the user profile or no user found");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
