package com.catService.authentication.kafkaListeners;

import com.catService.authentication.services.auth.AuthService;
import com.catService.contract.commands.auth.CreateToken;
import com.catService.contract.commands.auth.CreateUser;
import com.catService.contract.dto.auth.RegisteredUserDto;
import com.catService.contract.dto.auth.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@KafkaListener(topics = "auth", groupId = "auth-group")
public class AuthListener {
    private final AuthService authService;

    @KafkaHandler
    @SendTo
    public TokenDto createToken(CreateToken command) {
        return authService.createToken(command.userDto());
    }

    @KafkaHandler
    @SendTo
    public RegisteredUserDto createToken(CreateUser command) {
        return authService.createUser(command.userDto(), command.ownerDto());
    }
}
