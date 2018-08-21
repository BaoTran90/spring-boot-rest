package com.baotran.springboot.rest.example.springbootrest.common.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class SampleFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SampleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Remote Host: " + servletRequest.getRemoteHost());
        logger.info("Remote Address: " + servletRequest.getRemoteAddr());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
