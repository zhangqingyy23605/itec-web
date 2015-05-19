package edu.hust.itec.interceptor;

import edu.hust.itec.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xsh on 2015/5/11.
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User authUser = (User)session.getAttribute("auth");
        if (authUser == null) {
            response.sendRedirect("/auth/login");
            return false;
        }
        System.out.println(request.getRequestURI());
        return true;
    }
}