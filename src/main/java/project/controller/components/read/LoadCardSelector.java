package project.controller.components.read;

import project.entity.DigimonCard;
import project.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Servlet that grabs digimon card objects and forwards to the digimon cards page
 */
@WebServlet(
        urlPatterns = {"/cardselector"}
)
public class LoadCardSelector extends HttpServlet {
    /**
     * Handles HTTP GET requests
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession userSession = req.getSession();
        String url = "error";
        req.setAttribute("errorMessage", "ERROR: user is not logged in. Please sign in to use this feature.");
        if (userSession.getAttribute("userName") != null) {
            ServletContext servletContext = getServletContext();
            GenericDao genericDaoResponse = new GenericDao(DigimonCard.class);
            Properties apiPathProperties = (Properties) servletContext.getAttribute("apiPathProperties");
            String apiURL = apiPathProperties.getProperty("digimon.card.url.path");
            String param = apiPathProperties.getProperty("digimon.card.name.param");
            String paramValue = "";

            List<DigimonCard> retrievedCards = genericDaoResponse.getResponseWithParam(apiURL, param, paramValue);
            req.setAttribute("digimonCards", retrievedCards);

            url = "digimoncards.jsp";
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
