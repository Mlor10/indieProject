package project.controller.components;


import project.entity.Card;
import project.entity.Reply;
import project.entity.Thread;
import project.entity.User;
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
 * Servlet that deletes an item from the database using the retrieved info
 */
@WebServlet(
        urlPatterns = {"/delete"}
)
public class Delete extends HttpServlet {
    /**
     * Handles HTTP GET requests
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession userSession = req.getSession();
        String targetURL = "/error";
        req.setAttribute("errorMessage", "ERROR: user is not logged in. Please sign in to use this feature.");
        if (userSession.getAttribute("userName") != null) {
            String deleteObject = req.getParameter("deleteObject");
            int deleteValue = Integer.parseInt(req.getParameter("deleteValue"));
            GenericDao genericDaoUser = new GenericDao(User.class);
            // grabs the logged user
            List<User> loggedUser = genericDaoUser.getByPropertyEqual("userName", (String)userSession.getAttribute("userName"));
            targetURL = getServletContext().getContextPath();
            if (deleteObject.equals("user")) {
                User currentUser = (User) genericDaoUser.getById(deleteValue);

                // security check to see if the user is deleting their own account or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentUser.getUserName())) {
                    genericDaoUser.delete(currentUser);
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the user or have admin permissions to delete this user");
                }
            }
            if (deleteObject.equals("thread")) {
                GenericDao genericDaoThread = new GenericDao(Thread.class);
                Thread currentThread = (Thread) genericDaoThread.getById(deleteValue);
                // security check to see if the user is deleting their own thread or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentThread.getUser().getUserName())) {
                    genericDaoThread.delete(currentThread);
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the thread owner or have admin permissions to delete this thread");
                }
            }
            if (deleteObject.equals("reply")) {
                GenericDao genericDaoReply = new GenericDao(Reply.class);
                Reply currentReply = (Reply) genericDaoReply.getById(deleteValue);

                // security check to see if the user is deleting their own reply or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentReply.getUser().getUserName())) {
                    genericDaoReply.delete(currentReply);
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the reply owner or have admin permissions to delete this reply");
                }
            }
            if (deleteObject.equals("card")) {
                GenericDao genericDaoCard = new GenericDao(Card.class);
                Card currentCard = (Card) genericDaoCard.getById(deleteValue);

                // security check to see if the user is deleting their own reply or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentCard.getUser().getUserName())) {
                    genericDaoCard.delete(currentCard);
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the card owner or have admin permissions to delete this card");
                }
            }
        }
        resp.sendRedirect(targetURL);
    }
}
