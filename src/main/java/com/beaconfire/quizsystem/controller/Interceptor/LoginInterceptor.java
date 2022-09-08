package com.beaconfire.quizsystem.controller.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String isLogin = (String) session.getAttribute("isLogin");
        if(isLogin == null || !isLogin.equals("true")){
            response.sendRedirect("/user/toLogin");
            return false;
        }
        return true;
    }
}
