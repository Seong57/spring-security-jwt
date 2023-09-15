package com.example.sideporoject.filter;

import com.example.sideporoject.commom.error.TokenErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.domain.token.JwtUtils;
import com.example.sideporoject.domain.token.service.TokenService;
import com.example.sideporoject.domain.token.tokenhelper.TokenHelper;
import com.example.sideporoject.domain.user.entity.UserEntity;
import com.example.sideporoject.domain.user.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter  {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;


    /* @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String authorizationToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization token : {}", authorizationToken);

        if (authorizationToken == null) {
            log.info("authorization token이 없습니다.");
            chain.doFilter(request, response);
            return;
        }

        String userEmail = tokenService.validationToken(authorizationToken);
        log.info("userEmail : {}", userEmail);
        chain.doFilter(request, response);
    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization token : {}", authorizationToken);

        if (authorizationToken == null) {
            log.info("authorization token이 없습니다.");
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        String userEmail = jwtUtils.validationToken(authorizationToken);
        log.info("userEmail : {}", userEmail);


        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        System.out.println("authentication = " + authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
