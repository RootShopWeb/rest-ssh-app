package org.yourname.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс Spring Boot приложения.
 * Содержит точку входа в приложение и конфигурацию Spring Boot.
 */
@SpringBootApplication
public class PracticeApplication {

    /**
     * Точка входа в Spring Boot приложение.
     *
     * @param args аргументы командной строки, переданные при запуске приложения
     */
    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }
}