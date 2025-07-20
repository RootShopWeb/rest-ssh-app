package org.yourname.practice.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.yourname.practice.model.User;
import org.yourname.practice.repository.UserRepository;

/**
 * Компонент для инициализации начальных данных в приложении.
 * Создает административного пользователя при старте приложения, если он отсутствует.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Метод инициализации, выполняемый после создания бина.
     * Проверяет наличие пользователя с именем "admin" и создает его при отсутствии.
     * Пароль пользователя кодируется с помощью {@link PasswordEncoder}.
     *
     * @implNote Метод выполняется автоматически благодаря аннотации {@link PostConstruct}.
     * В случае успешного создания пользователя выводит сообщение в консоль.
     */
    @PostConstruct
    public void init() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin123"));
            userRepository.save(user);
            System.out.println("Пользователь admin добавлен");
        }
    }
}