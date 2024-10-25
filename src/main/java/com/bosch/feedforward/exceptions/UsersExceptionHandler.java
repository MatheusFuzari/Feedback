package com.bosch.feedforward.exceptions;

import ch.qos.logback.core.net.SocketConnector;
import com.bosch.feedforward.entity.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.http.WebSocket;


@ControllerAdvice
@Slf4j
public class UsersExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UserNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(
            UserException e, WebSocket request, HttpServletRequest servletRequest
    ){
        log.error("Error: {}, Message: {}, Method: {}, URL: {}",
                e.getCode(), e.getMessage(), servletRequest.getMethod(), servletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionResponse.builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build()
        );
    }
}
