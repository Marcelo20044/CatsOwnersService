package com.catService.presentation.commandMappers;

import com.catService.contract.commands.auth.CreateToken;
import com.catService.contract.commands.auth.CreateUser;
import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.contract.dto.auth.UserDto;
import org.springframework.stereotype.Component;

@Component
public class AuthCommandMapper {
    public CreateToken createToken(UserDto userDto) {
        return new CreateToken(userDto);
    }

    public CreateUser createUser(UserDto userDto, CreateOwnerDto ownerDto) {
        return new CreateUser(userDto, ownerDto);
    }
}
