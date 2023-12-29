package com.delivery.api.domain.token.service;

public interface TokenServiceIfs {

    String generateAccessToken(Long id);

    String generateRefreshToken(Long id);

    Long getUserIdFromToken(String token);

    boolean validateToken(String token);
}
