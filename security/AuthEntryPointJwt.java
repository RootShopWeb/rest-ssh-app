package org.yourname.practice.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Компонент обработки ошибок аутентификации JWT.
 * Отправляет HTTP-ответ 401 Unauthorized при неудачной аутентификации.
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    /**
     * Обрабатывает запрос при неудачной аутентификации.
     *
     * @param request HTTP-запрос, который вызвал ошибку аутентификации
     * @param response HTTP-ответ, в который будет записан результат обработки ошибки
     * @param authException исключение, вызвавшее ошибку аутентификации
     * @throws IOException если возникает ошибка ввода-вывода при работе с ответом
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}