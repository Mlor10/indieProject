package project.controller.components;


import project.entity.User;
import project.persistence.GenericDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet that updates a user info
 */
@WebServlet(
        urlPatterns = {"/update"}
)
public class Update extends HttpServlet {
    /**
     * Handles HTTP GET requests
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession userSession = req.getSession();
        String targetURL = "";
        String updateObject = req.getParameter("updateObject");
        String updateType = req.getParameter("updateType");
        String updateValue = req.getParameter("updateValue");
        if (updateObject.equals("user")) {
            GenericDao genericDaoUser = new GenericDao(User.class);
            int userId = (int)userSession.getAttribute("userId");
            User currentUser = (User)genericDaoUser.getById(userId);
            targetURL = req.getHeader("Referer");
            if (updateType.equals("firstName")) {
                currentUser.setFirstName(updateValue);
            }
            if (updateType.equals("lastName")) {
                currentUser.setLastName(updateValue);
            }
            if (updateType.equals("dateOfBirth")) {
                LocalDate updateDate = LocalDate.parse(updateValue);
                currentUser.setDateOfBirth(updateDate);
            }
            if (updateType.equals("profileImage")) {
                currentUser.setUserImage(updateValue);
                targetURL = getServletContext().getContextPath() + "/profile?userName=" + currentUser.getUserName();
            }
            genericDaoUser.saveOrUpdate(currentUser);
        }
        resp.sendRedirect(targetURL);
    }
}
