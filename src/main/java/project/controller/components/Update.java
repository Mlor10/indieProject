package project.controller.components;


import project.entity.User;
import project.entity.Thread;
import project.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession userSession = req.getSession();
        String targetURL = "/error";
        req.setAttribute("errorMessage", "ERROR: user is not logged in. Please sign in to use this feature.");
        if (userSession.getAttribute("userName") != null) {
            String updateObject = req.getParameter("updateObject");
            String updateType = req.getParameter("updateType");
            String updateValue = req.getParameter("updateValue");
            GenericDao genericDaoUser = new GenericDao(User.class);
            List<User> loggedUser = genericDaoUser.getByPropertyEqual("userName", (String)userSession.getAttribute("userName"));
            if (updateObject.equals("user")) {
                int userId = (int) userSession.getAttribute("userId");
                User currentUser = (User) genericDaoUser.getById(userId);
                targetURL = req.getHeader("Referer");
                // security check to see if the user is updating their own account or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentUser.getUserName())) {
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
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the user or have admin permissions to update this user");
                }
            }

            if (updateObject.equals("thread")) {
                GenericDao genericDaoThread = new GenericDao(Thread.class);
                int threadId = Integer.parseInt(req.getParameter("threadId"));
                Thread currentThread = (Thread) genericDaoThread.getById(threadId);
                targetURL = getServletContext().getContextPath() + "/thread?threadId=" + threadId;
                // security check to see if the user is updating their own thread or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentThread.getUser().getUserName())) {
                    if (updateType.equals("threadTitle")) {
                        currentThread.setThreadTitle(updateValue);
                    }
                    if (updateType.equals("threadContent")) {
                        currentThread.setThreadContent(updateValue);
                    }
                    if (updateType.equals("threadDate")) {
                        LocalDateTime updateDate = LocalDateTime.parse(updateValue);
                        currentThread.setThreadDate(updateDate);
                    }
                    if (updateType.equals("threadViews")) {
                        int updateViewValue = Integer.parseInt(updateValue);
                        currentThread.setThreadViews(updateViewValue);
                    }
                    genericDaoThread.saveOrUpdate(currentThread);
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the thread owner or have admin permissions to update this thread");
                }
            }
        }
        resp.sendRedirect(targetURL);
    }
}
