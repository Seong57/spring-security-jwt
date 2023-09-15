package com.example.sideporoject.domain.token;

import com.example.sideporoject.domain.token.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final TokenService tokenService;

    public String getUserEmail(String token, String key) {

        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();

    }

    public String validationToken(String accessToken){
            String userEmail = tokenService.validationToken(accessToken);
            return userEmail;
    }


}
