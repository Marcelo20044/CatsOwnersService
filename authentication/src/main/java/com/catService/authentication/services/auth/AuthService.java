package com.catService.authentication.services.auth;

import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.contract.dto.owner.OwnerDto;
import com.catService.contract.dto.auth.RegisteredUserDto;
import com.catService.contract.dto.auth.TokenDto;
import com.catService.contract.dto.auth.UserDto;

public interface AuthService {
    TokenDto createToken(UserDto userDto);

    RegisteredUserDto createUser(UserDto userDto, CreateOwnerDto ownerDto);
}
