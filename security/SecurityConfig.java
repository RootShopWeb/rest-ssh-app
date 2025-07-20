package org.yourname.practice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.yourname.practice.service.CustomUserDetailsService;

/**
 * Конфигурационный класс безопасности Spring Security.
 * Настраивает аутентификацию, авторизацию и защиту веб-запросов.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthTokenFilter authTokenFilter;

    /**
     * Конструктор конфигурации безопасности.
     *
     * @param customUserDetailsService сервис для загрузки данных пользователя
     * @param authTokenFilter JWT фильтр аутентификации
     */
    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          AuthTokenFilter authTokenFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.authTokenFilter = authTokenFilter;
    }

    /**
     * Бин для кодирования паролей.
     *
     * @return BCryptPasswordEncoder для хеширования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Настраивает провайдера аутентификации.
     *
     * @return DaoAuthenticationProvider с настроенным UserDetailsService и PasswordEncoder
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Бин для управления аутентификацией.
     *
     * @param authConfig конфигурация аутентификации
     * @return AuthenticationManager
     * @throws Exception если возникла ошибка при создании менеджера аутентификации
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Основная цепочка фильтров безопасности.
     *
     * @param http объект конфигурации HTTP безопасности
     * @return настроенный SecurityFilterChain
     * @throws Exception если возникла ошибка при настройке
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Отключаем CSRF защиту для REST API
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Без сессий
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // Разрешаем доступ к аутентификации
                        .anyRequest().authenticated()  // Все остальные запросы требуют аутентификации
                )
                .authenticationProvider(authenticationProvider())  // Наш провайдер аутентификации
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);  // Добавляем JWT фильтр

        return http.build();
    }
}