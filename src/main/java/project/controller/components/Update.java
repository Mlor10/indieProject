package project.controller.components;


import project.entity.Card;
import project.entity.Reply;
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
            GenericDao genericDaoUser = new GenericDao(User.class);
            String updateObject = req.getParameter("updateObject");
            String updateType = req.getParameter("updateType");
            String updateValue = req.getParameter("updateValue");
            int updateObjectId = Integer.parseInt(req.getParameter("updateObjectId"));
            List<User> loggedUser = genericDaoUser.getByPropertyEqual("userName", (String)userSession.getAttribute("userName"));
            if (updateObject.equals("user")) {
                User currentUser = (User) genericDaoUser.getById(updateObjectId);
                targetURL = req.getHeader("Referer");
                // security check to see if the user is updating their own account or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentUser.getUserName()) || loggedUser.get(0).getAdminPermission().equals("true")) {
                    if (updateType != null) {
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
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the user or have admin permissions to update this user");
                }
            }
            if (updateObject.equals("thread")) {
                GenericDao genericDaoThread = new GenericDao(Thread.class);
                String threadTitle = req.getParameter("threadTitle");
                String threadContent = req.getParameter("threadContent");
                Thread currentThread = (Thread) genericDaoThread.getById(updateObjectId);
                targetURL = getServletContext().getContextPath() + "/thread?threadId=" + updateObjectId;
                // security check to see if the user is updating their own thread or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentThread.getUser().getUserName()) || loggedUser.get(0).getAdminPermission().equals("true")) {
                    // checks for empty thread title or content to not update it
                    if (!threadTitle.equals("") && !threadContent.equals("")) {
                        currentThread.setThreadTitle(threadTitle);
                        currentThread.setThreadContent(threadContent);
                    } else if (!threadTitle.equals("") && threadContent.equals("")) {
                        currentThread.setThreadTitle(threadTitle);
                    } else if (threadTitle.equals("") && !threadContent.equals("")) {
                        currentThread.setThreadContent(threadContent);
                    }
                    if (updateType != null) {
                        if (updateType.equals("threadDate")) {
                            LocalDateTime updateDate = LocalDateTime.parse(updateValue);
                            currentThread.setThreadDate(updateDate);
                        }
                        if (updateType.equals("threadViews")) {
                            int updateViewValue = Integer.parseInt(updateValue);
                            currentThread.setThreadViews(updateViewValue);
                        }
                    }
                    genericDaoThread.saveOrUpdate(currentThread);
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the thread owner or have admin permissions to update this thread");
                }
            }
            if (updateObject.equals("reply")) {
                GenericDao genericDaoReply = new GenericDao(Reply.class);
                String replyTitle = req.getParameter("replyTitle");
                String replyContent = req.getParameter("replyContent");
                int threadId = Integer.parseInt(req.getParameter("threadId"));
                Reply currentReply = (Reply) genericDaoReply.getById(updateObjectId);
                targetURL = getServletContext().getContextPath() + "/thread?threadId=" + threadId;
                // security check to see if the user is updating their own reply or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentReply.getUser().getUserName()) || loggedUser.get(0).getAdminPermission().equals("true")) {
                    // checks for empty reply title or content to not update it
                    if (!replyTitle.equals("") && !replyContent.equals("")) {
                        currentReply.setReplyTitle(replyTitle);
                        currentReply.setReplyContent(replyContent);
                    } else if (!replyTitle.equals("") && replyContent.equals("")) {
                        currentReply.setReplyTitle(replyTitle);
                    } else if (replyTitle.equals("") && !replyContent.equals("")) {
                        currentReply.setReplyContent(replyContent);
                    }
                    if (updateType != null) {
                        if (updateType.equals("replyDate")) {
                            LocalDateTime updateDate = LocalDateTime.parse(updateValue);
                            currentReply.setReplyDate(updateDate);
                        }
                    }
                    genericDaoReply.saveOrUpdate(currentReply);
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the thread owner or have admin permissions to update this thread");
                }
            }
            if (updateObject.equals("card")) {
                GenericDao genericDaoCard = new GenericDao(Card.class);
                Card currentCard = (Card) genericDaoCard.getById(updateObjectId);
                targetURL = req.getHeader("Referer");
                // security check to see if the user is updating their own card or have admin permissions
                if (loggedUser.get(0).getUserName().equals(currentCard.getUser().getUserName()) || loggedUser.get(0).getAdminPermission().equals("true")) {
                    if (updateType != null) {
                        if (updateType.equals("cardName")) {
                            currentCard.setCardName(updateValue);
                        }
                        if (updateType.equals("cardPrice")) {
                            double updatePrice = Double.parseDouble(updateValue);
                            currentCard.setCardPrice(updatePrice);
                        }
                        if (updateType.equals("cardDescription")) {
                            currentCard.setCardDescription(updateValue);
                        }
                        genericDaoCard.saveOrUpdate(currentCard);
                    }
                } else {
                    req.setAttribute("errorMessage", "ERROR: not the card owner or have admin permissions to update this card");
                }
            }
        }
        resp.sendRedirect(targetURL);
    }
}
