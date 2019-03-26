package com.ozzot.jdbc;

import com.ozzot.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ozzot.utils.Constants.*;

public class ExecuteQuery {

    public static Object getAll() throws SQLException {

        List<User> usersList = null;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            usersList = new ArrayList<>();

            for (int i = 1; i < columnCount; i++) {

                while (resultSet.next()) {
                    usersList.add(
                            new User(resultSet.getInt("userId"),
                                    resultSet.getString("firstName"),
                                    resultSet.getString("lastName"),
                                    resultSet.getDate(i).toLocalDate(),
                                    resultSet.getString("email"),
                                    resultSet.getString("phoneNumber")));
                }
            }
        }
        return usersList;
    }

    public static Object getByID(int id) throws SQLException {

        User user = null;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i < columnCount; i++) {

                while (resultSet.next()) {
                    user = new User(resultSet.getInt("userId"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getDate("birth").toLocalDate(),
                            resultSet.getString("email"),
                            resultSet.getString("phoneNumber"));
                }
            }
        }

        return user;
    }

    public static void addUser(String name, String lastName, LocalDate birth, String email, String phone) throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setDate(3, Date.valueOf(birth));
            statement.setString(4, email);
            statement.setString(5, phone);

            int id = statement.executeUpdate();

            System.out.println(id);
        }
    }

    public static void deleteUser(int id) throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);

            int deletedUser = statement.executeUpdate();

            System.out.println(deletedUser);

        }
    }
}
