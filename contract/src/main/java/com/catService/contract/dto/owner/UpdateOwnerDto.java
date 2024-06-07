package com.catService.contract.dto.owner;

import java.time.LocalDate;

public record UpdateOwnerDto (
        Long id,
        String name,
        LocalDate birthDate,
        String username) {
}
