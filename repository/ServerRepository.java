package org.yourname.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yourname.practice.model.Server;

/**
 * Репозиторий для работы с сущностями Server в базе данных.
 * Предоставляет CRUD-операции и дополнительные методы для работы с серверами.
 */
public interface ServerRepository extends JpaRepository<Server, Long> {

    /**
     * Находит сервер по его имени.
     *
     * @param name имя сервера для поиска
     * @return найденный сервер или null, если сервер с указанным именем не существует
     */
    Server findByName(String name);
}