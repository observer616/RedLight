package com.ryan.redlight.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Ryan
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        // 检查注解
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            LoginCheck loginCheck = ((HandlerMethod) handler).getMethodAnnotation(LoginCheck.class);
            if (loginCheck == null) {
                return true;
            }
        }
        // 检查权限
        Boolean isLogin = (Boolean) (session.getAttribute("isLogin"));
        if (isLogin == null || !isLogin) {
            // todo response.sendRedirect("/login");
            response.sendError(403, "非管理员权限");
            return false;
        }
        return true;
    }
}
