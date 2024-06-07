package com.catService.contract.commands.owner;

import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.infrastructure.entities.User;

public record CreateOwner(User user, CreateOwnerDto ownerDto) {
}
