package com.catService.authentication.mappers;

import com.catService.contract.commands.owner.CreateOwner;
import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.infrastructure.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CommandMapper {
    public CreateOwner createOwner(User user, CreateOwnerDto ownerDto) {
        return new CreateOwner(user, ownerDto);
    }
}
