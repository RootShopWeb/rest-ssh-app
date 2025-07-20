package org.yourname.practice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO-класс для запроса выполнения SSH-команды на сервере.
 * Содержит информацию о целевом сервере и команде для выполнения.
 */
@Getter
@Setter
public class SSHRequest {
    /**
     * Имя сервера, на котором нужно выполнить команду.
     * Должно соответствовать имени, под которым сервер зарегистрирован в системе.
     */
    private String name;

    /**
     * Команда для выполнения на сервере через SSH.
     * Может содержать любые допустимые команды shell.
     */
    private String command;
}