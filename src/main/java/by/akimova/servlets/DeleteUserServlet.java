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

public class DeleteUserServlet extends HttpServlet {
    private Map<Integer, User> users;

    @Override
    public void init() throws ServletException {
        final Object users = getServletContext().getAttribute("users");
        if (users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("Your repo does not initialize!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }

        System.out.println("DeleteUserServlet is init");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        if (Utils.idIsNumber(req)) {
            resp.sendRedirect(req.getContextPath() + "/");
        }
        resp.sendRedirect(req.getContextPath() + "/");

        System.out.println("doPost is working");
    }
}
