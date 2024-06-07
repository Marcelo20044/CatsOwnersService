package com.catService.authentication.mappers;

import com.catService.contract.dto.auth.RegisteredUserDto;
import com.catService.contract.dto.auth.UserDto;
import com.catService.infrastructure.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public RegisteredUserDto toRegisteredUserDto(User user) {
        return new RegisteredUserDto(user.getId(), user.getUsername());
    }

    public User toEntity(UserDto user) {
        return new User(user.username(), passwordEncoder.encode(user.password()));
    }
}
