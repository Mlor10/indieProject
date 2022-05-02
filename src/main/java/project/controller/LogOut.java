package project.controller;

import project.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/logOut"}
)

/** Begins the authentication process using AWS Cognito
 *
 */
public class LogOut extends HttpServlet implements PropertiesLoader {
    Properties properties;
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGOUT_URL;
    public static String SIGNOUT_URL;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        this.properties = (Properties)context.getAttribute("cognitoProperties");
        CLIENT_ID = properties.getProperty("client.id");
        LOGOUT_URL = properties.getProperty("logoutURL");
        SIGNOUT_URL = properties.getProperty("signoutURL");
    }

    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession userSession = req.getSession();
        String url = "/error";
        if (userSession.getAttribute("userName") != null) {
            if (this.properties != null && LOGOUT_URL != null && CLIENT_ID != null && SIGNOUT_URL != null) {
                url = LOGOUT_URL + "?client_id=" + CLIENT_ID + "&logout_uri=" + SIGNOUT_URL;
                userSession.invalidate();
            } else {
                url = "/error";
            }
        }
        resp.sendRedirect(url);
    }
}
