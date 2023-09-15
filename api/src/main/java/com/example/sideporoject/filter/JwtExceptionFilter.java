package com.example.sideporoject.filter;

import com.example.sideporoject.commom.costomresponse.reponse.Response;
import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.error.ErrorCodeIfs;
import com.example.sideporoject.commom.error.TokenErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.commom.exception.JwtException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("JwtExceptionFilter request : {}" , request.getRequestURI());

        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            if (e.getErrorCodeIfs().getErrorCode() == 2000) {
                response(response, TokenErrorCode.INVALID_TOKEN);
            } else if (e.getErrorCodeIfs().getErrorCode() == 2001) {
                response(response, TokenErrorCode.EXPIRED_TOKEN);
            }else if (e.getErrorCodeIfs().getErrorCode() == 2002) {
                response(response, TokenErrorCode.TOKEN_EXCEPTION);
            }

        }
    }

    private void response(HttpServletResponse response, ErrorCodeIfs errorCodeIfs) throws IOException {
        Response apiResponse = Response.ERROR(errorCodeIfs);
        String responseBody = objectMapper.writeValueAsString(apiResponse);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(apiResponse.getResult().getResultCode());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseBody);
    }
}
