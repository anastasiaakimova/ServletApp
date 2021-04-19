package by.akimova.servlets;

import by.akimova.model.User;
import by.akimova.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class AddUserServlet extends HttpServlet {
    private Map<Integer, User> users;
    private AtomicInteger id;

    @Override
    public void init() throws ServletException {
        final Object users = getServletContext().getAttribute("users");
        if (users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("Your repo does not initialize!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }
        id = new AtomicInteger(2);

        System.out.println("AddUserServlet is init");

    }

    // получение данных с формы
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        if (Utils.requestIsValid(req)) {
            doGet(req, resp);

        final String name = req.getParameter("name");
        final String age = req.getParameter("age");

        final User user = new User(name, Integer.valueOf(age));
        final int id = this.id.getAndIncrement();
        users.setId(id);
        users.setAge(Integer.valueOf(age));
        users.setName(name);

        users.put(id, user);
    }
        resp.sendRedirect(req.getContextPath() + "/");
        System.out.println("doPost is working");
    }
}
