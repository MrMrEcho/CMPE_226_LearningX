package com.learnx.demo.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoggingConfig implements WebMvcConfigurer {

    Logger logger = LogManager.getLogger(LoggingConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**");
    }

    class LogInterceptor
            extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(
                HttpServletRequest request,
                HttpServletResponse response,
                Object handler) {
            logger.log(Level.INFO, "Receive request: " + request.getRequestURI());
            logger.log(Level.INFO, "Response status: " + response.getStatus());
            return true;
        }

        @Override
        public void afterCompletion(
                HttpServletRequest request,
                HttpServletResponse response,
                Object handler,
                Exception ex) {
            logger.log(Level.INFO, "Receive request: " + request.getRequestURI());
            logger.log(Level.INFO, "Response status: " + response.getStatus());
        }
    }

}
