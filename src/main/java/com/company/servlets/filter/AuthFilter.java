package com.company.servlets.filter;

import com.company.dao.UserDAO;
import com.company.model.Restaurant;
import com.company.model.User;
import com.company.model.kitchen.Cook;
import com.company.model.kitchen.Order;
import com.company.util.AppUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;
/**
 * Acidification filter.
 */
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("now do filter AuthFilt");
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        @SuppressWarnings("unchecked")
        Restaurant restaurant= (Restaurant) req.getServletContext().getAttribute("restaurant");
        AtomicReference<UserDAO> dao = null;
                    dao = restaurant.getDao();

        final HttpSession session = req.getSession();
        //Logged user.
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            final User.ROLE role = (User.ROLE) session.getAttribute("role");
            final int id=dao.get().getUserID((String) session.getAttribute("login"),(String) session.getAttribute("password"));
            moveToMenu(req, res, role,id);
        } else if (dao.get().userIsExist(login, password)) {
            System.out.println("не знаем роль и ищем(doFiltr AuthFiltr)");
            final User.ROLE role = dao.get().getRoleByLoginPassword(login, password);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            moveToMenu(req, res, role,dao.get().getUserID((String) session.getAttribute("login"),(String) session.getAttribute("password")));
        } else {
            moveToMenu(req, res, User.ROLE.UNKNOWN,-3);
        }
    }
    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'user' move to user menu.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final User.ROLE role,
                            final int id)
            throws ServletException, IOException {
       User user=new User();
       user.setPassword((String) req.getSession().getAttribute("password"));
       user.setRole(role);
       user.setLogin((String)req.getSession().getAttribute("login"));
       user.setId(id);
       System.out.println("now do move to menu AuthFilt ---- create user for session"+ user.getLogin());
       AppUtils.setSessionUserParam(req.getSession(),role,user.getLogin(),user, id);
        if (role.equals(User.ROLE.ADMIN)) {
            req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);
        } else if (role.equals(User.ROLE.COOK)) {
            req.getSession().setAttribute("user", user);
            user.setCook(new Cook(user.getLogin()));
            req.getRequestDispatcher("/WEB-INF/view/cook_menu.jsp").forward(req, res);
        } else if (role.equals(User.ROLE.WAITER)) {
            if (req.getSession().getAttribute("order")==null){
                req.getSession().setAttribute("order",new Order(user));
            }
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/view/waiter_menu.jsp").forward(req, res);
        } else if (role.equals(User.ROLE.TABLET_TABLE)) {
        if (req.getSession().getAttribute("order")==null){
            req.getSession().setAttribute("order",new Order(user));
        }
        req.getSession().setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/view/table_menu.jsp").forward(req, res);
    } else {
            AppUtils.setSessionUserParam(req.getSession(), User.ROLE.UNKNOWN,null,user,-2);
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }
    @Override
    public void destroy() {
    }
}
