package by.akimova.util;

import by.akimova.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Utils {
    public static boolean idIsNumber(HttpServletRequest request) {
        final String id = request.getParameter("id");
        return id != null &&
                (id.length() > 0) &&
                id.matches("[+]?\\d+");
    }

    public static boolean requestIsValid(HttpServletRequest request) {
        return null;
    }

    public static User createStubUser(final int id, final String name, final int age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    public static  boolean idIsInvalid(final String id, Map<Integer, User> repo){
        return null;
    }
}
