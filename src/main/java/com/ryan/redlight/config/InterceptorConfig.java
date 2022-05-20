package com.ryan.redlight.config;

import com.ryan.redlight.interceptor.AdminInterceptor;
import com.ryan.redlight.interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ryan
 */
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor());
        registry.addInterceptor(new AdminInterceptor());
    }
}
