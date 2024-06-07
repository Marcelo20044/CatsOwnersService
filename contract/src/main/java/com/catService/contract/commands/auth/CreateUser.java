package com.catService.contract.commands.auth;

import com.catService.contract.dto.auth.UserDto;
import com.catService.contract.dto.owner.CreateOwnerDto;

public record CreateUser(UserDto userDto, CreateOwnerDto ownerDto) {
}
