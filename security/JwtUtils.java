package org.yourname.practice.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Утилитный класс для работы с JWT токенами.
 * Обеспечивает генерацию, валидацию и извлечение данных из токенов.
 */
@Component
public class JwtUtils {
    @Value("${app.jwt.secret}")
    private String jwtSecret;  // Секретный ключ для подписи токенов

    @Value("${app.jwt.expiration-ms}")
    private int jwtExpirationMs;  // Время жизни токена в миллисекундах

    /**
     * Генерирует JWT токен для указанного имени пользователя.
     *
     * @param username имя пользователя (subject токена)
     * @return сгенерированный JWT токен
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Извлекает имя пользователя из JWT токена.
     *
     * @param token JWT токен
     * @return имя пользователя (subject токена)
     * @throws JwtException если токен невалиден или не может быть обработан
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Проверяет валидность JWT токена.
     *
     * @param token токен для проверки
     * @return true если токен валиден, false в противном случае
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("JWT error: " + e.getMessage());
        }
        return false;
    }
}