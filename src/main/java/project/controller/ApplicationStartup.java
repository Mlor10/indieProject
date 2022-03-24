package project.controller;

import project.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

/**
 *  Application Startup
 */
@WebServlet(
        urlPatterns = {"/cognito-startup"},
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * init method that starts the servlet
     * @exception ServletException if there is a servlet failure
     */
    public void init() throws ServletException {
        Properties properties = loadProperties("/cognito.properties");
        // sets attribute into servlet context
        getServletContext().setAttribute("cognitoProperties", properties);
    }
}
