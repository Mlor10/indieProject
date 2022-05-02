package project.controller;

import project.persistence.GenericDao;
import project.entity.Thread;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that loads the user profile and forwards to the userprofile page
 */
@WebServlet(
        urlPatterns = {"/thread"}
)
public class LoadThread extends HttpServlet {

    /**
     * Handles HTTP GET requests
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao genericDaoThread = new GenericDao(Thread.class);
        int threadId = Integer.parseInt(req.getParameter("threadId"));
        String url;
        Thread currentThread = (Thread)genericDaoThread.getById(threadId);

        // increases thread view by one while loading the page
        currentThread.setThreadViews(currentThread.getThreadViews() + 1);
        genericDaoThread.saveOrUpdate(currentThread);

        // checks the searched thread if there are any before inserting into the database
        if (currentThread != null) {
            req.setAttribute("currentThread", currentThread);
            url = "userthread.jsp";
        } else {
            url = "error.jsp";
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
