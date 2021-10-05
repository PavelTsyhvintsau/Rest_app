package com.company.servlets.servlet;

import com.company.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ActivateCookServlet extends HttpServlet {
    protected void doPost ( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean bool = false;
        if(req.getParameter("act").equals("true"))bool=true;
        if(req.getParameter("act").equals("false"))bool=false;
        ((User)req.getSession().getAttribute("user")).getCook().setBusy(bool);
        resp.sendRedirect(req.getContextPath()+"/cooking_page" );
    }

}



