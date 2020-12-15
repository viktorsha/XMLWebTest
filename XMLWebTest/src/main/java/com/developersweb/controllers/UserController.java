package com.developersweb.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.developersweb.database.CreateXML;
import com.developersweb.database.Repository;
import com.developersweb.database.DevelopersRepositoryImpl;
import com.developersweb.entities.Developers;
import com.developersweb.parsers.IParser;
import com.developersweb.parsers.ParserFactory;
import com.developersweb.parsers.ServiceLocator;


@WebServlet(name="UserServlet",urlPatterns="/displayInfo")
public class UserController extends HttpServlet {
    public UserController()
    {
        super();
    }
    public void init() throws ServletException {}
    public static ServiceLocator Compose()
    {
        return ServiceLocator.getInstance()
                .register(ParserFactory.class, new ParserFactory());
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CreateXML.createXML();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        String fileName = req.getParameter("fileName");
        String parserName = req.getParameter("parser");
        if(fileName != null && parserName != null) {
            var serviceLocator = Compose();
            try {
                ParserFactory parserFactory = serviceLocator.resolve(ParserFactory.class);
                IParser parser = parserFactory.create(parserName);
                List<Developers> developersInfo = parser.getDevelopersInfo();
                Repository repository = new DevelopersRepositoryImpl();
                for (int i = 1; i< developersInfo.size(); i++)
                {
                    //repository.insert(developersInfo.get(i));
                }
                req.setAttribute("developers", developersInfo);
                req.getRequestDispatcher("/main.jsp").forward(req, resp);
                PrintWriter out;
            } catch (Exception e) {
                resp.sendRedirect("/index.jsp");
            }

        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    public void destroy()
    {
        super.destroy();
    }

}
