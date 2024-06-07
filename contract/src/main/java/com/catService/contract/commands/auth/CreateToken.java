package com.catService.contract.commands.auth;

import com.catService.contract.dto.auth.UserDto;

public record CreateToken(UserDto userDto) {
}
