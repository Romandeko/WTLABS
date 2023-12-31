package org.intensio.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.intensio.beans.Attributes;
import org.intensio.beans.Role;
import org.intensio.beans.User;
import org.intensio.beans.UserPrincipal;
import org.intensio.controller.command.AdminCommands;
import org.intensio.controller.command.AuthCommands;
import org.intensio.controller.command.Commands;
import org.intensio.service.ServiceFactory;
import org.intensio.service.UserService;
import org.intensio.service.exception.ServiceException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    UserService userService = ServiceFactory.getInstance().getUserService();

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        UserPrincipal userPrincipal = (UserPrincipal) httpServletRequest.getSession().getAttribute(Attributes.USER_PRINCIPAL);
        Optional<User> user = Optional.empty();
        if(userPrincipal != null) {
            try {
                user = userService.getUserByUsername(userPrincipal.getUsername());
            } catch (ServiceException ignored) {}
        }
        Role userRole;
        if(user.isPresent() && Objects.equals(userPrincipal.getToken(), user.get().getToken())){
            userRole = user.get().getRole();
        }
        else{
            userRole = Role.NOT_AUTH;
        }
        String command = httpServletRequest.getParameter(Attributes.COMMAND);
        if(Arrays.stream(AdminCommands.values()).anyMatch(str -> str.toString().equals(command))){
            if(userRole != Role.ADMIN){
                ((HttpServletResponse)servletResponse).sendRedirect("?command="+ Commands.MAIN);
                return;
            }
        }
        if(Arrays.stream(AuthCommands.values()).anyMatch(str -> str.toString().equals(command))){
            if(userRole != Role.ADMIN && userRole != Role.USER){
                ((HttpServletResponse)servletResponse).sendRedirect("?command="+ Commands.SIGN_IN_PAGE);
                return;
            }
        }
        httpServletRequest.setAttribute("role", userRole.toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
