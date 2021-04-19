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
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/hello")
public class FirstServlet extends HttpServlet {

    private static final String index = "/WEB-INF/view/index.jsp";
    private List<User> users;

    @Override
    public void init() throws ServletException {
        users = new CopyOnWriteArrayList<>();
        users.add(new User("Java", 10));
        users.add(new User("Vision", 20));
        System.out.println("Servlet is init");
    }

    // отображаем страницу
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("users", users);
        req.getRequestDispatcher(index).forward(req, resp);
        System.out.println("doGet is working");
    }

    // получение данных с формы
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        if (!requestIsValid(req)) {
            doGet(req, resp);

        }

        final String name = req.getParameter("name");
        final String age = req.getParameter("age");
        final User user = new User(name, Integer.valueOf(age));

        users.add(user);
        doGet(req, resp);

        System.out.println("doPost is working");
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
