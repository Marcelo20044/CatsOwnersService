package com.catService.owner.mappers;

import com.catService.contract.commands.cat.CreateCatWithOwner;
import com.catService.contract.commands.cat.IsOwnedByWithOwner;
import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.owner.ValidateId;
import com.catService.infrastructure.entities.Owner;
import org.springframework.stereotype.Component;

@Component
public class CommandMapper {
    public IsOwnedByWithOwner isOwnedByWithOwner(Long catId, Owner owner) {
        return new IsOwnedByWithOwner(catId, owner);
    }

    public CreateCatWithOwner createCatWithOwner(CatDto catDto, Owner owner) {
        return new CreateCatWithOwner(catDto, owner);
    }
}
