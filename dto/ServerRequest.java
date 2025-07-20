package org.yourname.practice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO-класс для запроса создания/обновления сервера.
 * Содержит основные параметры сервера, необходимые для подключения.
 * <p>
 * Использует Lombok для автоматической генерации методов доступа.
 */
@Getter
@Setter
public class ServerRequest {
    /**
     * Уникальное имя сервера (идентификатор)
     */
    private String name;

    /**
     * IP-адрес сервера для подключения
     */
    private String ip;

    /**
     * Имя пользователя для авторизации на сервере
     */
    private String username;

    /**
     * Пароль для авторизации на сервере.
     * Должен передаваться и храниться в зашифрованном виде.
     */
    private String password;
}