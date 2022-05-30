package com.ryan.redlight.interceptor;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.vo.Msg;
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
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            // 检查注解
            AdminCheck adminCheck = ((HandlerMethod) handler).getMethodAnnotation(AdminCheck.class);
            if (adminCheck != null) {
                // 检查权限
                HttpSession session = request.getSession();
                Admin admin = (Admin) session.getAttribute("adminInfo");
                if (admin == null) {
                    response.addHeader("msg", new Msg("权限不足").toString());
                    response.sendRedirect("/admin/login");
//            response.sendError(403, "非管理员权限");
                    return false;
                }
            }
        }
        return true;
    }
}
