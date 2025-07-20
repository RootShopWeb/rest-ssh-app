package org.yourname.practice.dto;

/**
 * DTO класс для передачи данных JWT аутентификации.
 * Содержит JWT токен и базовую информацию о пользователе.
 */
public class JwtResponse {
    private String token;
    private Long id;
    private String username;

    /**
     * Конструктор для создания объекта ответа с JWT токеном.
     *
     * @param token JWT токен
     * @param id идентификатор пользователя
     * @param username имя пользователя
     */
    public JwtResponse(String token, Long id, String username) {
        this.token = token;
        this.id = id;
        this.username = username;
    }

    /**
     * Получает JWT токен.
     *
     * @return строку с JWT токеном
     */
    public String getToken() {
        return token;
    }

    /**
     * Устанавливает JWT токен.
     *
     * @param token строка с JWT токеном
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Получает идентификатор пользователя.
     *
     * @return идентификатор пользователя
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор пользователя.
     *
     * @param id идентификатор пользователя
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получает имя пользователя.
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }
}