package org.yourname.practice.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.yourname.practice.model.User;

import java.util.Collection;
import java.util.Objects;

/**
 * Реализация UserDetails для интеграции пользовательской модели User с Spring Security.
 * Содержит основные данные пользователя, необходимые для аутентификации и авторизации.
 */
public class UserDetailsImpl implements UserDetails {

    @Getter
    private final Long id;          // Уникальный идентификатор пользователя
    private final String username;  // Логин пользователя
    private final String password;  // Зашифрованный пароль

    /**
     * Конструктор реализации UserDetails.
     *
     * @param id идентификатор пользователя
     * @param username логин пользователя
     * @param password зашифрованный пароль
     */
    public UserDetailsImpl(Long id, String username, String password /*, authorities*/) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Создает UserDetailsImpl из сущности User.
     *
     * @param user сущность пользователя
     * @return объект UserDetailsImpl
     */
    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;  // В текущей реализации роли не используются
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Аккаунт никогда не истекает
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Аккаунт никогда не блокируется
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Учетные данные никогда не истекают
    }

    @Override
    public boolean isEnabled() {
        return true;  // Аккаунт всегда активен
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailsImpl user)) return false;
        return Objects.equals(id, user.id);  // Сравнение по ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);  // Хеш-код на основе ID
    }
}