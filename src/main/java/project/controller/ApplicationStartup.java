package project.controller;

import project.utilities.PropertiesLoader;

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

    /**
     * init method that sets property file content into servlet context scope
     */
    public void init() {
        Properties cognitoProperties = loadProperties("/cognito.properties");
        Properties apiPathProperties = loadProperties("/apipath.properties");
        // sets attributes into servlet context
        getServletContext().setAttribute("cognitoProperties", cognitoProperties);
        getServletContext().setAttribute("apiPathProperties", apiPathProperties);
    }
}
