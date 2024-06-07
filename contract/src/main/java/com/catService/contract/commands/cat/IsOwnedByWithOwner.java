package com.catService.contract.commands.cat;

import com.catService.infrastructure.entities.Owner;

public record IsOwnedByWithOwner(Long id, Owner owner) {
}
