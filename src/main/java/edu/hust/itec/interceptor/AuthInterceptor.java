package edu.hust.itec.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xsh on 2015/5/11.
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        // set few parameters to handle ajax request from different host
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.addHeader("Access-Control-Max-Age", "1000");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Cache-Control", "private");

        String reqUri = request.getRequestURI();
        String serviceName = reqUri.substring(reqUri.lastIndexOf("/") + 1,
                reqUri.length());
        if (serviceName.equals("SOMETHING")) {

        }
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//
//        super.postHandle(request, response, handler, modelAndView);
//    }
}