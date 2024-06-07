package com.catService.contract.dto.auth;

import com.catService.contract.dto.owner.CreateOwnerDto;

public record RegistrationRequest(UserDto userDto, CreateOwnerDto ownerDto) {
}
