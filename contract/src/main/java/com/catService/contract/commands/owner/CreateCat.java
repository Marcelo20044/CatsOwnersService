package com.catService.contract.commands.owner;

import com.catService.contract.dto.cat.CatDto;

public record CreateCat(CatDto catDto, Long ownerId) {
}
