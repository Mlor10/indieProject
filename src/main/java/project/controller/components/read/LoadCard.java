package project.controller.components.read;

import project.entity.Card;
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
 * Servlet that loads the user card and forwards to the cardinfo page
 */
@WebServlet(
        urlPatterns = {"/card"}
)
public class LoadCard extends HttpServlet {

    /**
     * Handles HTTP GET requests
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao genericDaoCard = new GenericDao(Card.class);
        int cardId = Integer.parseInt(req.getParameter("cardId"));
        String url;
        Card searchCard = (Card)genericDaoCard.getById(cardId);
        if (searchCard != null) {
            req.setAttribute("currentCard", searchCard);
            url = "cardinfo.jsp";
        } else {
            url = "error.jsp";
            req.setAttribute("errorMessage", "ERROR: could not load the card information or no card found");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
