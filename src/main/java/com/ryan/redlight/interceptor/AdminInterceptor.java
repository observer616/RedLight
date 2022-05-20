package com.ryan.redlight.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author RyanO
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        // 检查注解
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            AdminCheck adminCheck = ((HandlerMethod) handler).getMethodAnnotation(AdminCheck.class);
            if (adminCheck == null) {
                return true;
            }
        }
        // 检查权限
        Boolean isAdmin = (Boolean) (session.getAttribute("isAdmin"));
        if (isAdmin == null || !isAdmin) {
            // TODO: 2022/5/13 1 redirect
            response.sendRedirect("/login");
//            response.sendError(403, "非管理员权限");
            return false;
        }
        return true;
    }
}
