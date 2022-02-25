package project.controller;
import project.entity.Card;
import project.entity.User;
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
        urlPatterns = {"/searchEntity"}
)

public class SearchEntity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // grabs the search term from the form
        String searchTerm = req.getParameter("searchTerm");

        GenericDao userDao = new GenericDao(User.class);
        GenericDao cardDao = new GenericDao(Card.class);

        // checks if search term is empty or not to display the search results or all the users
        // username property name is filler
        if (searchTerm != null) {
            req.setAttribute("users", userDao.getByPropertyLike("userName", searchTerm));
            req.setAttribute("cards", cardDao.getByPropertyLike("cardName", searchTerm));
        } else {
            req.setAttribute("users", userDao.getAllEntities());
            req.setAttribute("cards", cardDao.getAllEntities());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}