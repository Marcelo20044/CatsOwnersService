package com.catService.contract.dto.owner;

import java.time.LocalDate;

public record CreateOwnerDto(String name, LocalDate birthDate) {
}
