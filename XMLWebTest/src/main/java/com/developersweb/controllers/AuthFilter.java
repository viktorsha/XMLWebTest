package com.developersweb.controllers;

import com.developersweb.database.Repository;
import com.developersweb.database.UserRepositoryImpl;
import com.developersweb.entities.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        try
        {
            final String login = req.getParameter("login");
            final String password = req.getParameter("password");

            final HttpSession session = req.getSession();
            Repository repository = new UserRepositoryImpl();
            if (nonNull(session) && nonNull(session.getAttribute("login")) &&
                    nonNull(session.getAttribute("password")))
            {
                final User.ROLE role = (User.ROLE) session.getAttribute("role");
                moveToMenu(req, resp, role);

            }
            else {
                User user = (User) repository.findByCredentials(login, password);
                if (!user.getLogin().equals("")) {

                    final User.ROLE role = user.getRole();
                    req.getSession().setAttribute("password", password);
                    req.getSession().setAttribute("login", login);
                    req.getSession().setAttribute("role", role);
                    moveToMenu(req, resp, role);

                } else {
                    moveToMenu(req, resp, User.ROLE.UNKNOWN);
                }
        }
        } catch (Exception throwables) {
            resp.sendRedirect("/");
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final User.ROLE role)
            throws ServletException, IOException {


        if (role.equals(User.ROLE.ADMIN)|| role.equals(User.ROLE.USER)) {

            req.getRequestDispatcher("/index.jsp").forward(req, res);
            //res.sendRedirect("/index.jsp");

        } else {

            req.getRequestDispatcher("/register.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
