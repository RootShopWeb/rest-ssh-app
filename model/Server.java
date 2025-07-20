package org.yourname.practice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность, представляющая сервер в системе.
 * Соответствует таблице "servers" в базе данных.
 */
@Getter
@Setter
@Entity
@Table(name = "servers")
public class Server {

    /**
     * Уникальный идентификатор сервера.
     * Генерируется автоматически при сохранении в БД.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Уникальное имя сервера.
     * Не может быть null и должно быть уникальным.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * IP-адрес сервера.
     */
    private String ip;

    /**
     * Имя пользователя для подключения к серверу.
     */
    private String username;

    /**
     * Пароль для подключения к серверу.
     * В реальной системе должен храниться в зашифрованном виде.
     */
    private String password;

    /**
     * Конструктор по умолчанию.
     * Требуется для JPA/Hibernate.
     */
    public Server() {}
}