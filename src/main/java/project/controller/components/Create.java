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
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Servlet that updates a user info
 */
@WebServlet(
        urlPatterns = {"/create"}
)
public class Create extends HttpServlet {
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
        String createObject = req.getParameter("createObject");

        // checks if a user is logged in before attempting to create a thread
        if (userSession.getAttribute("userName") != null) {
            int userId = (int)userSession.getAttribute("userId");
            GenericDao genericDaoUser = new GenericDao(User.class);
            GenericDao genericDaoThread = new GenericDao(Thread.class);
            targetURL = req.getHeader("Referer");
            if (createObject.equals("thread")) {
                String threadTitle = req.getParameter("threadTitle");
                String threadContent = req.getParameter("threadContent");

                Thread newThread = new Thread(threadTitle, threadContent, 0, LocalDateTime.now(Clock.systemUTC()).truncatedTo(ChronoUnit.SECONDS), (User)genericDaoUser.getById(userId));
                genericDaoThread.insert(newThread);
            }
            if (createObject.equals("reply")) {
                String replyTitle = req.getParameter("replyTitle");
                String replyContent = req.getParameter("replyContent");
                int threadId = Integer.parseInt(req.getParameter("threadId"));
                GenericDao genericDaoReply = new GenericDao(Reply.class);

                Reply newReply = new Reply(replyTitle, replyContent, LocalDateTime.now(Clock.systemUTC()).truncatedTo(ChronoUnit.SECONDS), (Thread)genericDaoThread.getById(threadId), (User)genericDaoUser.getById(userId));
                genericDaoReply.insert(newReply);
            }
            if (createObject.equals("card")) {
                String cardName = req.getParameter("cardName");
                String cardImage = req.getParameter("cardImage");
                GenericDao genericDaoCard = new GenericDao(Card.class);
                Card newCard = null;
                if (!cardImage.equals("") && cardImage != null) {
                    newCard = new Card(cardName, null, 0, cardImage, (User)genericDaoUser.getById(userId));
                } else {
                    newCard = new Card(cardName, null, 0, null, (User)genericDaoUser.getById(userId));
                }
                genericDaoCard.insert(newCard);
                targetURL = getServletContext().getContextPath() + "/cards";
            }
        }
        resp.sendRedirect(targetURL);
    }
}
