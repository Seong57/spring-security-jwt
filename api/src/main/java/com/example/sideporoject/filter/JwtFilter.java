package com.example.sideporoject.filter;

import com.example.sideporoject.security.token.service.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter  {

    private final TokenService tokenService;
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
            filterChain.doFilter(request, response);
            return;
        }

        String userEmail = tokenService.validationTokenWithUserEmail(authorizationToken);
        log.info("userEmail : {}", userEmail);


        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        System.out.println("authentication = " + authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
