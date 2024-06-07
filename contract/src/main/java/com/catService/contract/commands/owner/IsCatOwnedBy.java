package com.catService.contract.commands.owner;

public record IsCatOwnedBy(Long catId, String username) {
}
