package org.yourname.practice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO-класс для ответа с информацией о сервере.
 * Содержит основные данные сервера для отображения клиенту.
 * <p>
 * Использует Lombok для автоматической генерации геттеров.
 * Сеттеры также генерируются, но основной способ создания объекта - через конструктор.
 */
@Getter
@Setter
public class ServerResponse {
    /**
     * Уникальное имя сервера
     */
    private String name;

    /**
     * IP-адрес сервера
     */
    private String ip;

    /**
     * Конструктор для создания ответа с данными сервера
     * @param name имя сервера
     * @param ip IP-адрес сервера
     */
    public ServerResponse(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }
}