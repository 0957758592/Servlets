package com.ozzot.userstore.web.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SelectUserServletTest {

    private static final String EMAIL = "mail@mail.com";
    private static final String USER_NAME = "USER";
    private static final String BIRTH_DATE = "2019-12-12";

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    public void doGet() throws IOException {
        //prepare
        when(request.getParameter("id")).thenReturn("8");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        // when
        new SelectUserServlet().doGet(request, response);

        //then
        assertTrue(stringWriter.toString().contains(EMAIL));
        assertTrue(stringWriter.toString().contains(USER_NAME));
        assertTrue(stringWriter.toString().contains(BIRTH_DATE));

    }
}