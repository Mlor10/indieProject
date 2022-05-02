package project.controller;

import project.entity.Digimon;
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
 * Servlet that grabs digimon objects and forwards to the profile images page
 */
@WebServlet(
        urlPatterns = {"/profileimages"}
)
public class LoadProfileImage extends HttpServlet {
    /**
     * Handles HTTP GET requests
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession userSession = req.getSession();
        String url = "/error";
        if (userSession.getAttribute("userName") != null) {
            ServletContext servletContext = getServletContext();
            GenericDao genericDaoResponse = new GenericDao(Digimon.class);
            Properties apiPathProperties = (Properties) servletContext.getAttribute("apiPathProperties");
            String apiURL = apiPathProperties.getProperty("digimon.image.url.path");
            String param = "";
            String paramValue = "";

            List<Digimon> retrievedDigimons = genericDaoResponse.getResponseWithParam(apiURL, param, paramValue);
            req.setAttribute("digimons", retrievedDigimons);

            url = "digimonimages.jsp";
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
