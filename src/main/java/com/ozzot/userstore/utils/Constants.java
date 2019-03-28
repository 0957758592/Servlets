package com.ozzot.userstore.utils;

import java.time.format.DateTimeFormatter;

public class Constants {

    public static final String URL = "jdbc:mysql://localhost:3306/testDB?autoReconnect=true&useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "1234567890QQQ";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String GET_ALL = "SELECT * from users;";
    public static final String GET_BY_ID = "SELECT userId,  firstName, lastName, birth, email, phoneNumber from users where userId=?;";
    public static final String ADD_USER = "INSERT INTO users (firstName, lastName, birth, email, phoneNumber) values (?,?,?,?,?);";
    public static final String DELETE = "DELETE from users where userId=?;";
    public static final String UPDATE = "UPDATE users SET firstName=?, lastName=?, birth=?, email=?, phoneNumber=? WHERE userId=?;";


}
