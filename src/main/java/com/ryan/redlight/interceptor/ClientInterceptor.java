package com.ryan.redlight.interceptor;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Client;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Ryan
 */
public class ClientInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        // 检查注解
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            ClientCheck clientCheck = ((HandlerMethod) handler).getMethodAnnotation(ClientCheck.class);
            if (clientCheck == null) {
                return true;
            }
        }
        // 检查权限
        Client client = (Client) session.getAttribute("clientInfo");
        Admin admin = (Admin) session.getAttribute("adminInfo");
        if (client == null && admin == null) {
            response.sendRedirect("/login");
//            response.sendError(403, "非管理员权限");
            return false;
        }
        return true;
    }
}
