package com.catService.presentation.commandMappers;

import com.catService.contract.commands.owner.CreateCat;
import com.catService.contract.commands.owner.IsCatOwnedBy;
import com.catService.contract.commands.owner.UpdateOwner;
import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.owner.UpdateOwnerDto;
import org.springframework.stereotype.Component;

@Component
public class OwnerCommandMapper {
    public IsCatOwnedBy isCatOwnedBy(Long catId, String username) {
        return new IsCatOwnedBy(catId, username);
    }

    public UpdateOwner updateOwner(UpdateOwnerDto updateOwnerDto) {
        return new UpdateOwner(updateOwnerDto);
    }

    public CreateCat createCat(CatDto catDto, Long ownerId) {
        return new CreateCat(catDto, ownerId);
    }
}
