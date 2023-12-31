package org.intensio.controller.command.Impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.intensio.beans.Attributes;
import org.intensio.beans.UserPrincipal;
import org.intensio.controller.command.Command;
import org.intensio.service.ServiceFactory;
import org.intensio.service.UserService;
import org.intensio.service.exception.ServiceException;
import org.intensio.service.exception.UnknownUserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignInCommand implements Command {

    UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        String email = request.getParameter("email");
        if (email == null || email.length() == 0) {
            errors.put("email", "Incorrect email");
        }
        String password = request.getParameter("password");
        if (password == null || password.length() < 3) {
            errors.put("password", "Incorrect password, length should be more then 8");
        }
        if (errors.size() == 0) {
            try {
                UserPrincipal userPrincipal = userService.authenticate(email, password);
                if (userPrincipal == null) {
                    request.setAttribute("Error", "Incorrect password");
                    return "WEB-INF/view/signIn.jsp";
                }
                request.getSession().setAttribute(Attributes.USER_PRINCIPAL, userPrincipal);
                request.setAttribute("role", userPrincipal.getRole().toString());
                return "index.jsp";
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            } catch (UnknownUserException e) {
                request.setAttribute("Error", "Unknown user, please check email and password");
            }
        }
        request.setAttribute("errors", errors);
        return "WEB-INF/view/signIn.jsp";
    }
}
