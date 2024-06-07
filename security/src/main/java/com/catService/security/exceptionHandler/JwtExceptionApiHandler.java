package com.catService.security.exceptionHandler;

import com.catService.contract.dto.ExceptionResponseDto;
import com.catService.contract.exceptions.auth.InvalidTokenSignException;
import com.catService.contract.exceptions.auth.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExceptionApiHandler {
    @ExceptionHandler({TokenExpiredException.class, InvalidTokenSignException.class})
    public ResponseEntity<ExceptionResponseDto> handleTokenExceptions(RuntimeException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
