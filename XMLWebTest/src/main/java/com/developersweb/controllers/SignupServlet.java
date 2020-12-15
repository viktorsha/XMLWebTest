package com.developersweb.controllers;

import com.developersweb.database.Repository;
import com.developersweb.database.UserRepositoryImpl;
import com.developersweb.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final HttpSession session = req.getSession();
        final User.ROLE role;
        if (!login.equals("")&&!password.equals(""))
        {
            Repository repository = new UserRepositoryImpl();
            User user = new User(login, password, User.ROLE.ADMIN);
            repository.insert(user);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", User.ROLE.ADMIN);
        }
        //req.getRequestDispatcher("/index.jsp").forward(req, resp);
        resp.sendRedirect("/index.jsp");
    }
}
