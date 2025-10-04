package com.bach2k2.volunteer.configurations.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.List;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtTokenProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
   
    @Value("${spring.secret-key}")
    private String secretKey; // should be at least 256 bits for HS256

    @Bean
    public SecretKey jwtSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        System.err.println("JWT Secret Key: " + secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(String username, Long userId, List<String> roles){
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new java.util.Date())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(jwtSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    
    // Keep the old method for backwards compatibility
    // public String generateJwtToken(String username){
    //     return generateJwtToken(username, null, java.util.Arrays.asList("USER"));
    // }

    public Long getUserIdFromToken(String token) {
        try {
            Claims claims= Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Long.parseLong(claims.get("userId").toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return (List<String>) claims.get("roles");
        } catch (Exception e) {
            return java.util.Arrays.asList("USER");
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }


    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes())).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {

            log.error("Invalid JWT token - {}", ex.getMessage());
        }catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

}
