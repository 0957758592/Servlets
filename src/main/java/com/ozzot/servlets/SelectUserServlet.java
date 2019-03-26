package com.ozzot.servlets;

import com.ozzot.jdbc.ExecuteQuery;
import com.ozzot.templates.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SelectUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Map<String, Object> user = new HashMap<>();

        try {
            user.put("user", ExecuteQuery.getByID(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getWriter().write(PageGenerator.instance().getPage("user.html", user));

    }
}
