package com.catService.contract.dto.owner;

import com.catService.contract.dto.cat.CatDto;

import java.time.LocalDate;
import java.util.Set;

public record OwnerDto(
        Long id,
        String name,
        LocalDate birthDate,
        Set<CatDto> cats,
        String username) {
}
