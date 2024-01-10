package com.delivery.api.domain.token.service;

import com.delivery.api.common.error.TokenErrorCode;
import com.delivery.api.common.exception.ApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TokenService implements TokenServiceIfs {

    @Value("${jwt.secret-key}")
    private String secret;
    @Value("${jwt.access-token-expiration-seconds}")
    private Long accessTokenExpirationSeconds;
    @Value("${jwt.refresh-token-expiration-seconds}")
    private Long refreshTokenExpirationSeconds;

    @Override
    public String generateAccessToken(Long id) {

        var validity = Date.from(new Date().toInstant().plusSeconds(accessTokenExpirationSeconds));

        return generateToken(validity, id);

    }

    @Override
    public String generateRefreshToken(Long id) {

        var validity = Date.from(new Date().toInstant().plusSeconds(refreshTokenExpirationSeconds));

        return generateToken(validity, id);
    }

    @Override
    public Long getUserIdFromToken(String token) {
        var key = Keys.hmacShaKeyFor(secret.getBytes());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    @Override
    public boolean validateToken(String token) {
        try {
            var key = Keys.hmacShaKeyFor(secret.getBytes());

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (SecurityException | IllegalArgumentException e) {
            throw new ApiException(TokenErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new ApiException(TokenErrorCode.EXPATRIATE_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new ApiException(TokenErrorCode.UNSUPPORTED_TOKEN);
        }
    }


    private String generateToken(Date expirationDate, Long id) {
        var key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .setSubject(id.toString())
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
