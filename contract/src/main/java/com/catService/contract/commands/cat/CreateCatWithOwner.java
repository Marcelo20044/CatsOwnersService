package com.catService.contract.commands.cat;

import com.catService.contract.dto.cat.CatDto;
import com.catService.infrastructure.entities.Owner;

public record CreateCatWithOwner(CatDto catDto, Owner owner) {
}
