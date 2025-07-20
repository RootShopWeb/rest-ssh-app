package org.yourname.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yourname.practice.model.User;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностями User (пользователи) в базе данных.
 * Наследует стандартные CRUD-операции от JpaRepository и добавляет
 * специализированные методы для работы с пользователями.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по его логину (username).
     * Использует Optional для безопасной обработки случая, когда пользователь не найден.
     *
     * @param username логин пользователя для поиска
     * @return Optional, содержащий найденного пользователя или пустой, если пользователь не найден
     */
    Optional<User> findByUsername(String username);
}