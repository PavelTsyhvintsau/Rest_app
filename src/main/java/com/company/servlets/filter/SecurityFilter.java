package com.company.servlets.filter;

import com.company.model.User;
import com.company.util.AppUtils;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.logging.LogRecord;

public class SecurityFilter implements Filter {
    public SecurityFilter() {
    }
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();
        User.ROLE role = AppUtils.getSessionRole(((HttpServletRequest) req).getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        if (role != null) {
            // User Name
            String userName = AppUtils.getSessionLogin(request.getSession());
        }
        // Страницы требующие входа в систему.
        if (SecurityConfig.getUrlPatternsAllSecurityPages().contains(servletPath)) {
            // Если пользователь еще не вошел в систему,
            // Redirect (перенаправить) к странице логина.
            if (role == null) {
                String requestUri = request.getRequestURI();
                System.out.println(requestUri+"Если пользователь еще не вошел в систему,Redirect (перенаправить) к странице логина.");
                request.getServletContext().getRequestDispatcher("/");
                return;
            }
            // Проверить пользователь имеет действительную роль или нет?
            boolean hasPermission=SecurityConfig.getUrlPatternsForRole(role.toString()).contains(servletPath);
                System.out.println("haPermission "+hasPermission);
            if (hasPermission) {
                System.out.println("права пользователя подтверждены");
                chain.doFilter(request, response);
                return;
            }

            System.out.println("права пользователя не подтверждены"+hasPermission);
            RequestDispatcher dispatcher= request.getServletContext().getRequestDispatcher("/WEB-INF/view/access_denied.jsp");
            dispatcher.forward(request, response);
        }
        if (!SecurityConfig.getUrlPatternsAllSecurityPages().contains(servletPath)) {
            if(servletPath.startsWith("/image")) {
                System.out.println("........................pasth start /images ");
                chain.doFilter(request, response);
                return;
            }
            req.setAttribute("path", servletPath);
            RequestDispatcher dispatcher= request.getServletContext().getRequestDispatcher("/WEB-INF/view/unknown_address.jsp");
            dispatcher.forward(request, response);
        }
    }
    public void init(FilterConfig fConfig) throws ServletException {
    }
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
