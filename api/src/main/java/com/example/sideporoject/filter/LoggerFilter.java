package com.example.sideporoject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        log.info("INIT URI : {}", req.getRequestURI());

        chain.doFilter(req, res);

        // request 정보
        Enumeration<String> headerNames = req.getHeaderNames();
        StringBuilder headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(key -> {
            String headerValue = req.getHeader(key);

            headerValues
                    .append("[")
                    .append(key)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        });

        String requestURI = req.getRequestURI();
        String method = req.getMethod();

        log.info(">>>>> uri : {} , method : {} , header : {}", requestURI, method, headerValues);

        StringBuilder responseHeaderValues = new StringBuilder();

        Collection<String> resHeaderNames = res.getHeaderNames();

        res.getHeaderNames().forEach(key -> {
            String headerValue = res.getHeader(key);

            responseHeaderValues
                    .append("[")
                    .append(key)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        });


    }

}
