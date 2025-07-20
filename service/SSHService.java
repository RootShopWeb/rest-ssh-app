package org.yourname.practice.service;

import org.yourname.practice.model.Server;
import org.yourname.practice.repository.ServerRepository;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

/**
 * Сервис для выполнения SSH-команд на удаленных серверах.
 * Обеспечивает безопасное подключение и выполнение команд через SSH протокол.
 */
@Service
public class SSHService {
    private final ServerRepository serverRepository;

    /**
     * Конструктор SSH сервиса.
     *
     * @param serverRepository репозиторий для получения данных о серверах
     */
    public SSHService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    /**
     * Выполняет команду на указанном сервере через SSH.
     *
     * @param serverName имя сервера из базы данных
     * @param command команда для выполнения
     * @return результат выполнения команды или сообщение об ошибке
     * @throws IllegalArgumentException если сервер с указанным именем не найден
     * @throws Exception при ошибках SSH соединения или выполнения команды
     */
    public String executeCommand(String serverName, String command) throws Exception {
        Server server = serverRepository.findByName(serverName);
        if (server == null) {
            throw new IllegalArgumentException("Сервер не найден: " + serverName);
        }

        Session session = null;
        ChannelExec channel = null;

        try {
            JSch jsch = new JSch();
            session = jsch.getSession(server.getUsername(), server.getIp(), 22);
            session.setPassword(server.getPassword());
            session.setConfig("StrictHostKeyChecking", "no"); // Отключаем проверку host key
            session.connect(5000); // Таймаут подключения 5 секунд

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            // Настраиваем потоки вывода
            ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
            ByteArrayOutputStream errorBuffer = new ByteArrayOutputStream();
            channel.setOutputStream(outputBuffer);
            channel.setErrStream(errorBuffer);

            channel.connect();

            // Ждем завершения выполнения команды
            while (!channel.isClosed()) {
                Thread.sleep(100);
            }

            // Проверяем ошибки
            String errorOutput = errorBuffer.toString();
            if (!errorOutput.isEmpty()) {
                return "Ошибка: " + errorOutput;
            }

            // Возвращаем результат
            return outputBuffer.toString();

        } finally {
            // Гарантированное закрытие соединений
            if (channel != null) channel.disconnect();
            if (session != null) session.disconnect();
        }
    }
}