package project.controller.components.read;

import project.entity.Card;
import project.entity.Thread;
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
 * Servlet that loads the latest cards and threads to the homepage
 */
@WebServlet(
        urlPatterns = {""}
)
public class HomePage extends HttpServlet {

    /**
     * Handles HTTP GET requests
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao genericDaoThread = new GenericDao(Thread.class);
        GenericDao genericDaoCard = new GenericDao(Card.class);
        String url = "index.jsp";

        // sublist that contains the last three elements
        List<Card> latestCards = genericDaoCard.getAllEntities().subList(Math.max(genericDaoCard.getAllEntities().size() - 3, 0), genericDaoCard.getAllEntities().size());
        List<Thread> latestThreads = genericDaoThread.getAllEntities().subList(Math.max(genericDaoThread.getAllEntities().size() - 3, 0), genericDaoThread.getAllEntities().size());
        req.setAttribute("cards", latestCards);
        req.setAttribute("threads", latestThreads);

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
