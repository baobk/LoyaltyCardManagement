package com.neo.project.LoyaltyCard.authentication.jwt;

import com.neo.project.LoyaltyCard.service.UserService;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JWTTokenProvider {

    private final String JWT_SECRET = "baobk";
    private final long JWT_EXPIRATION = 604800000L;
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Autowired
    UserService userService;

    // Tạo ra jwt từ thông tin user
    public String generateToken() {
        Date expiryDate = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUser().getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
}
