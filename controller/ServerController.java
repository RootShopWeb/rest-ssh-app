package org.yourname.practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yourname.practice.dto.ServerRequest;
import org.yourname.practice.model.Server;
import org.yourname.practice.service.ServerService;

/**
 * Контроллер для управления серверами (виртуальными машинами).
 * Предоставляет REST API для добавления, получения и удаления серверов.
 */
@RestController
@RequestMapping("/api/vm")
public class ServerController {

    private final ServerService serverService;

    /**
     * Конструктор контроллера серверов.
     *
     * @param serverService сервис для работы с серверами
     */
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    /**
     * Добавляет новый сервер на основе переданных данных.
     *
     * @param serverRequest DTO с данными сервера (имя, пароль, IP, имя пользователя)
     * @return ResponseEntity с созданным сервером
     */
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ServerRequest serverRequest) {
        Server server = new Server();
        server.setName(serverRequest.getName());
        server.setPassword(serverRequest.getPassword());
        server.setIp(serverRequest.getIp());
        server.setUsername(serverRequest.getUsername());
        return ResponseEntity.ok(serverService.add(server));
    }

    /**
     * Возвращает список всех серверов.
     *
     * @return ResponseEntity со списком всех серверов
     */
    @GetMapping("")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(serverService.findAll());
    }

    /**
     * Удаляет сервер по его имени.
     *
     * @param name имя сервера для удаления
     * @return ResponseEntity с пустым телом и статусом OK
     */
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        serverService.delete(name);
        return ResponseEntity.ok().build();
    }
}