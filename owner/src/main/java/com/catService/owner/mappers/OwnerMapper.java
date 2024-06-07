package com.catService.owner.mappers;

import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.contract.dto.owner.OwnerDto;
import com.catService.contract.dto.owner.UpdateOwnerDto;
import com.catService.infrastructure.entities.Owner;
import com.catService.infrastructure.entities.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OwnerMapper {

    public OwnerDto toDto(Owner owner) {
        return new OwnerDto(
                owner.getId(),
                owner.getName(),
                owner.getBirthDate(),
                owner.getCats().stream()
                        .map(cat -> new CatDto(cat.getId(), cat.getName(),
                                cat.getBirthDate(), cat.getBreed(), cat.getColor()))
                        .collect(Collectors.toSet()),
                owner.getUsername());
    }

    public Owner toEntity(CreateOwnerDto owner, User user) {
        return new Owner(
                owner.name(),
                owner.birthDate(),
                user.getUsername(),
                user);
    }

    public Owner toEntity(UpdateOwnerDto owner) {
        return new Owner(
                owner.id(),
                owner.name(),
                owner.birthDate(),
                owner.username());
    }
}
