package com.example.sideporoject.config.security;

import com.example.sideporoject.domain.token.JwtUtils;
import com.example.sideporoject.commom.exhandler.JwtAccessDeniedHandler;
import com.example.sideporoject.config.security.entrypoint.JwtAuthenticationEntryPoint;
import com.example.sideporoject.domain.token.service.TokenService;
import com.example.sideporoject.filter.JwtFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(config -> {
                    config.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(new JwtFilter(tokenService, userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeRequest -> {
                    authorizeRequest.requestMatchers("/api/user/**").hasAnyRole("USER", "MASTER");
                    authorizeRequest.requestMatchers("/api/admin/**").hasRole("MASTER");
                })
                .authorizeHttpRequests(authorizeRequest -> {
                    //authorizeRequest.requestMatchers("/open-api/**").permitAll();
                    authorizeRequest.anyRequest().permitAll();
                })
                .exceptionHandling(configurer -> {
                    configurer.accessDeniedHandler(new JwtAccessDeniedHandler(objectMapper));
                    configurer.authenticationEntryPoint(new JwtAuthenticationEntryPoint(objectMapper));
                })



                .build();

    }


}
