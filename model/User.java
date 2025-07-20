package org.yourname.practice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность, представляющая пользователя системы.
 * Соответствует таблице "users" в базе данных.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    /**
     * Уникальный идентификатор пользователя.
     * Генерируется автоматически при сохранении в БД.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Логин пользователя для входа в систему.
     * Должен быть уникальным.
     */
    private String username;

    /**
     * Пароль пользователя.
     * Хранится в зашифрованном виде (с использованием хеширования).
     */
    private String password;

    /**
     * Конструктор по умолчанию.
     * Требуется для работы JPA/Hibernate.
     */
    public User() {}
}