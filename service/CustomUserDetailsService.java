package org.yourname.practice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yourname.practice.model.User;
import org.yourname.practice.repository.UserRepository;
import org.yourname.practice.security.UserDetailsImpl;

/**
 * Кастомная реализация UserDetailsService для загрузки пользовательских данных.
 * Интегрирует Spring Security с пользовательской моделью User.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Конструктор сервиса.
     *
     * @param userRepository репозиторий для работы с пользователями
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Загружает данные пользователя по имени пользователя.
     *
     * @param username имя пользователя для поиска
     * @return UserDetails с данными пользователя
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }
}