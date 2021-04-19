package by.akimova.servlets;

import by.akimova.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/hello")
public class GetIndexPageServlet extends HttpServlet {

    private static final String index = "/WEB-INF/view/index.jsp";
    private Map<Integer, User> users;

    @Override
    public void init() throws ServletException {
        final Object users = getServletContext().getAttribute("users");
        if(users == null || !(users instanceof ConcurrentHashMap)){
            throw new IllegalStateException("Your repo does not initialize!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }
        System.out.println("Servlet is init");
    }

    // отображаем страницу
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("users", users.values());
        req.getRequestDispatcher(index).forward(req, resp);
        System.out.println("doGet is working");
    }



    private boolean requestIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("name");
        final String age = req.getParameter("age");

        return name != null && name.length() > 0 &&
                age != null && age.length() > 0 &&
                age.matches("[+]?\\d+");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet is destroy");
    }
}
