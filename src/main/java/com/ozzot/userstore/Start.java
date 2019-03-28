package com.ozzot.userstore;

import com.ozzot.userstore.web.servlets.SelectUserServlet;
import com.ozzot.userstore.web.servlets.UserStoreServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Start {

    public static void main(String[] args) throws Exception {
        UserStoreServlet userStoreServlet = new UserStoreServlet();
        SelectUserServlet userServlet = new SelectUserServlet();

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.addServlet(new ServletHolder(userStoreServlet), "/users/");
        handler.addServlet(new ServletHolder(userServlet), "/users/user");

        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
    }
}
