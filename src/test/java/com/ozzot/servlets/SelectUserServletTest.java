package com.ozzot.servlets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SelectUserServletTest {

    @Mock
   private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGet() throws IOException {

        when(request.getParameter("id")).thenReturn("3");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        new SelectUserServlet().doGet(request,response);

        verify(request, atLeast(1)).getParameter("id");
        printWriter.flush();

        System.out.println(stringWriter.toString());
        assertTrue(stringWriter.toString().contains("10@1010.10"));

    }
}