package com.ozzot.userstore.web.servlets;

import com.ozzot.userstore.dao.jdbc.ExecuteQuery;
import com.ozzot.userstore.web.templator.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ozzot.userstore.utils.Constants.FORMATTER;

public class UserStoreServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, Object> users = new HashMap<>();
        try {

            users.put("userList", ExecuteQuery.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getWriter().write(PageGenerator.instance().getPage("index.html", users));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String action = req.getParameter("actionName");

        if (action.equals("deleteUser")) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                ExecuteQuery.deleteUser(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {

            String name = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            LocalDate birth = LocalDate.parse(req.getParameter("birth"), FORMATTER);
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");

            try {
                ExecuteQuery.addUser(name, lastName, birth, email, phone);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("/users/");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));

        List<String> list = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("name")) {
                reader.readLine();
                list.add(reader.readLine());
            }
        }

        String name = list.get(0);
        String lastName = list.get(1);
        LocalDate birth = LocalDate.parse(list.get(2), FORMATTER);
        String email = list.get(3);
        String phone = list.get(4);
        int id = Integer.parseInt(list.get(5));

        ExecuteQuery.update(name, lastName, birth, email, phone, id);

    }
}
