package com.example.demo.web.exception.handler;

import org.springframework.http.HttpStatus; // ← ★ これを追加
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.AuthenticationException;
import com.example.demo.web.exception.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401 を返す
    public ErrorResponse handleAuthenticationException(AuthenticationException exception) {
        exception.printStackTrace(); // デバッグ用。不要なら削除してOK
        return new ErrorResponse("認証に失敗しました");
    }
}
