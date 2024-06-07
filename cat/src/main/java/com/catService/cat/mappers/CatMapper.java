package com.catService.cat.mappers;

import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.cat.CatWithFriendsDto;
import com.catService.infrastructure.entities.Cat;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CatMapper {
    public CatWithFriendsDto toDtoWithFriends(Cat cat) {
        return new CatWithFriendsDto(
                cat.getId(),
                cat.getName(),
                cat.getBirthDate(),
                cat.getBreed(),
                cat.getColor(),
                cat.getFriends().stream()
                        .map(c -> new CatDto(c.getId(), c.getName(), c.getBirthDate(),
                                c.getBreed(), c.getColor()))
                        .collect(Collectors.toSet()));
    }

    public CatDto toDto(Cat cat) {
        return new CatDto(
                cat.getId(),
                cat.getName(),
                cat.getBirthDate(),
                cat.getBreed(),
                cat.getColor());
    }

    public Cat toEntity(CatDto cat) {
        return new Cat(
                cat.id(),
                cat.name(),
                cat.birthDate(),
                cat.breed(),
                cat.color());
    }
}