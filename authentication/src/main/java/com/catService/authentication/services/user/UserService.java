package com.catService.authentication.services.user;

import com.catService.contract.dto.auth.RegisteredUserDto;
import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.contract.dto.auth.UserDto;
import com.catService.infrastructure.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getByUsername(String username);

    RegisteredUserDto create(UserDto registrationUserDto, CreateOwnerDto ownerDto);
}
