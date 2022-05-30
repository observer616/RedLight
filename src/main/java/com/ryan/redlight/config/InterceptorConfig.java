package com.ryan.redlight.config;

import com.ryan.redlight.interceptor.AdminInterceptor;
import com.ryan.redlight.interceptor.ClientInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ryan
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ClientInterceptor()).excludePathPatterns("/static/**", "/admin/login",
                "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");
        registry.addInterceptor(new AdminInterceptor()).excludePathPatterns("/static/**", "/admin/login",
                "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");
    }
}
