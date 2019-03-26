package com.ozzot.servlets;

import com.ozzot.jdbc.ExecuteQuery;
import com.ozzot.templates.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.ozzot.utils.Constants.FORMATTER;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("actionName");

        if(action.equals("deleteUser")){
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

}
