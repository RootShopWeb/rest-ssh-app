package org.yourname.practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yourname.practice.dto.SSHRequest;
import org.yourname.practice.service.SSHService;

/**
 * Контроллер для выполнения SSH команд на удаленных серверах.
 * Предоставляет API для выполнения команд через SSH соединение.
 */
@RestController
@RequestMapping("/api/ssh")
public class SSHController {

    private final SSHService sshService;

    /**
     * Конструктор контроллера SSH операций.
     *
     * @param sshService сервис для выполнения SSH операций
     */
    public SSHController(SSHService sshService) {
        this.sshService = sshService;
    }

/**
 * Выполняет команду на удаленном сервере через SSH.
 *
 * @param sshRequest DTO запроса содержащий имя сервера и команду для выполнения
 * @return ResponseEntity с результатом выполнения команды или сообщением об ошибке
 *         в случае возникновения исключения
 * @throws Exception если произошла ошибка при выполнении SSH команды
 */
@PostMapping("/execute")
public ResponseEntity<?> execute(@RequestBody SSHRequest sshRequest) {
    try {
        return ResponseEntity.ok(sshService.executeCommand(sshRequest.getName(), sshRequest.getCommand()));
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
                .body("Error executing command: " + e.getMessage());
    }
}
}