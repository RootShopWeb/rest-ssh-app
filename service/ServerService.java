package org.yourname.practice.service;

import org.springframework.stereotype.Service;
import org.yourname.practice.dto.ServerRequest;
import org.yourname.practice.dto.ServerResponse;
import org.yourname.practice.model.Server;
import org.yourname.practice.repository.ServerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с серверами (виртуальными машинами).
 * Обеспечивает основные CRUD-операции с серверами.
 */
@Service
public class ServerService {

    private final ServerRepository serverRepository;

    /**
     * Конструктор сервиса.
     *
     * @param serverRepository репозиторий для работы с серверами в БД
     */
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    /**
     * Добавляет новый сервер в систему.
     *
     * @param server объект сервера для сохранения
     * @return ServerResponse с основными данными сохраненного сервера
     */
    public ServerResponse add(Server server) {
        Server server1 = serverRepository.save(server);
        return new ServerResponse(server1.getName(), server1.getIp());
    }

    /**
     * Удаляет сервер по его имени.
     *
     * @param name имя сервера для удаления
     * @return true если сервер был найден и удален, false если сервер не найден
     */
    public boolean delete(String name) {
        Server server = serverRepository.findByName(name);
        if (server != null) {
            serverRepository.delete(server);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Получает список всех серверов в системе.
     *
     * @return список ServerResponse с основными данными всех серверов
     */
    public List<ServerResponse> findAll() {
        List<Server> list = serverRepository.findAll();
        List<ServerResponse> requests = new ArrayList<>();
        for (Server server : list) {
            requests.add(new ServerResponse(server.getName(), server.getIp()));
        }
        return requests;
    }
}