package by.akimova.servlets;

import by.akimova.model.User;
import by.akimova.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UpdateUserServlet extends HttpServlet {
    private Map<Integer, User> users;

    @Override
    public void init() throws ServletException {
        final Object users = getServletContext().getAttribute("users");
        if (users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("Your repo does not initialize!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }

        System.out.println("UpdateUserServlet is init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String id = req.getParameter("id");
        if (Utils.idIsInvalid(id, users)) {
            resp.sendRedirect(req.getContextPath() + "/");
        }
        final User user = users.get(Integer.parseInt(id));
        req.setAttribute("user", user);

        req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
        System.out.println("doGet is working");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");

            final String name = req.getParameter("name");
            final String age = req.getParameter("age");

            users.get(Integer.parseInt(id)).setName(name);

        resp.sendRedirect(req.getContextPath() + "/");
        System.out.println("doPost is working");
    }
}
