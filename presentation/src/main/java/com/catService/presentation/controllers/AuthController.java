package com.catService.presentation.controllers;

import com.catService.contract.dto.auth.RegistrationRequest;
import com.catService.presentation.commandMappers.AuthCommandMapper;

import com.catService.contract.dto.auth.RegisteredUserDto;
import com.catService.contract.dto.auth.TokenDto;
import com.catService.contract.dto.auth.UserDto;
import com.catService.kafka.services.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final KafkaService kafkaService;
    private final AuthCommandMapper authCommandMapper;

    private final String TOPIC = "auth";

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> createToken(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(kafkaService.sendSync(
                authCommandMapper.createToken(userDto),
                TOPIC,
                TokenDto.class
        ));
    }

    @PostMapping("/registration")
    public ResponseEntity<RegisteredUserDto> createUser(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(kafkaService.sendSync(
                authCommandMapper.createUser(registrationRequest.userDto(), registrationRequest.ownerDto()),
                TOPIC,
                RegisteredUserDto.class
        ));
    }
}