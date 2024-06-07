package com.catService.contract.dto.cat;

import com.catService.infrastructure.entities.Color;

import java.time.LocalDate;
import java.util.Set;

public record CatWithFriendsDto(
        Long id,
        String name,
        LocalDate birthDate,
        String breed,
        Color color,
        Set<CatDto> friends) {
}
