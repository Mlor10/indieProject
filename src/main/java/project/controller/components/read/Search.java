package project.controller.components.read;
import project.entity.Card;
import project.entity.User;
import project.entity.Thread;
import project.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Servlet to the results jsp page
 */

@WebServlet(
        urlPatterns = {"/search"}
)

public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // grabs the search term from the form
        String searchTerm = req.getParameter("searchTerm");

        GenericDao userDao = new GenericDao(User.class);
        GenericDao cardDao = new GenericDao(Card.class);
        GenericDao threadDao = new GenericDao(Thread.class);

        if (!searchTerm.equals("")) {
            req.setAttribute("users", userDao.getByPropertyLike("userName", searchTerm));
            req.setAttribute("cards", cardDao.getByPropertyLike("cardName", searchTerm));
            req.setAttribute("threads", threadDao.getByPropertyLike("threadTitle", searchTerm));
        } else {
            req.setAttribute("users", userDao.getAllEntities());
            req.setAttribute("cards", cardDao.getAllEntities());
            req.setAttribute("threads", threadDao.getAllEntities());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("results.jsp");
        dispatcher.forward(req, resp);
    }
}