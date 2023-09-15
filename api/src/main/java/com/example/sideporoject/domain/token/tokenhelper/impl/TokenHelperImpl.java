package com.example.sideporoject.domain.token.tokenhelper.impl;

import com.example.sideporoject.commom.error.TokenErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.domain.token.dto.TokenDto;
import com.example.sideporoject.domain.token.tokenhelper.TokenHelper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenHelperImpl implements TokenHelper {

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;



    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {

        LocalDateTime expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);

        Date expiredAt = Date.from(
            expiredLocalDateTime.atZone(
                    ZoneId.systemDefault()
            ).toInstant()
        );

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        String jwtToken = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setClaims(data)
            .setExpiration(expiredAt)
            .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();

    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {

        LocalDateTime expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);

        Date expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        String jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();

    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        JwtParser parser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build();

        try {
            Jws<Claims> result = parser.parseClaimsJws(token);
            log.info("Jws result : {}", result.getBody());
            return new HashMap<String, Object>(result.getBody());

        } catch (Exception e) {

            if (e instanceof SignatureException) {
                throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);
            }
            else if (e instanceof ExpiredJwtException) {
                throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);
            }
            else {
                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION);
            }
        }
    }
}
