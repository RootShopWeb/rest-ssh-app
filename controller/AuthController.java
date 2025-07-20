package org.yourname.practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yourname.practice.security.JwtUtils;
import org.yourname.practice.security.UserDetailsImpl;
import org.yourname.practice.dto.LoginRequest;
import org.yourname.practice.dto.JwtResponse;

/**
 * Контроллер для обработки запросов аутентификации пользователей.
 * Предоставляет endpoint для входа в систему и получения JWT токена.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * Конструктор контроллера аутентификации.
     *
     * @param authenticationManager менеджер аутентификации Spring Security
     * @param jwtUtils утилита для работы с JWT токенами
     */
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    /**
     * Аутентифицирует пользователя и возвращает JWT токен.
     *
     * @param loginRequest объект запроса с данными для входа (username и password)
     * @return ResponseEntity с JWT токеном, ID пользователя и его именем
     * @throws org.springframework.security.core.AuthenticationException если аутентификация не удалась
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication.getName());

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername()));
    }
}