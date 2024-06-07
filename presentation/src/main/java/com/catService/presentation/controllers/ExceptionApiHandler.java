package com.catService.presentation.controllers;

import com.catService.contract.dto.ExceptionResponseDto;
import com.catService.contract.exceptions.auth.*;
import com.catService.contract.exceptions.cat.CatAndFriendSameException;
import com.catService.contract.exceptions.cat.CatNotExistException;
import com.catService.contract.exceptions.kafka.SyncKafkaRequestException;
import com.catService.contract.exceptions.owner.OwnerNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidAuthDataException.class, InvalidTokenSignException.class,
            TokenExpiredException.class, UserNotExistException.class})
    public ResponseEntity<ExceptionResponseDto> handleUnauthorizedStatusExceptions(RuntimeException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({CatAndFriendSameException.class, RoleNotExistException.class, UserExistException.class,
            CatNotExistException.class, OwnerNotExistException.class, SyncKafkaRequestException.class})
    public ResponseEntity<ExceptionResponseDto> handleBadRequestStatusExceptions(RuntimeException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException() {
        return new ResponseEntity<>("В доступе отказано", HttpStatus.FORBIDDEN);
    }

}
