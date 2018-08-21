package com.baotran.springboot.rest.example.springbootrest.common.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ServiceInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("ServiceInterceptor - Pre handle method is calling");
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        logger.info("ServiceInterceptor - Post handle method is calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
        logger.info("ServiceInterceptor - Request and response is completed");
    }
}
