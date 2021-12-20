package com.company.servlets.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout.
 * Delete session.
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        final HttpSession session = req.getSession();
System.out.println("do /logout servlet");
        Enumeration<String> atr=session.getAttributeNames();
        while (atr.hasMoreElements()){
            String padam=atr.nextElement();
            System.out.println(padam);
            session.removeAttribute(padam);
        }


        resp.sendRedirect(req.getContextPath() + "/");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
