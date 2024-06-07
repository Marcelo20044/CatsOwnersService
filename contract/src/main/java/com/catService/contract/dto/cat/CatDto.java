package com.catService.contract.dto.cat;

import com.catService.infrastructure.entities.Color;

import java.time.LocalDate;

public record CatDto(
        Long id,
        String name,
        LocalDate birthDate,
        String breed,
        Color color) {
}
