package com.catService.owner.services;

import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.contract.dto.owner.OwnerDto;
import com.catService.contract.dto.owner.UpdateOwnerDto;
import com.catService.infrastructure.entities.User;

import java.util.List;

public interface OwnerService {
    OwnerDto getById(Long id);

    List<OwnerDto> getAll();

    Boolean isCatOwnedBy(Long catId, String username);

    OwnerDto create(User user, CreateOwnerDto ownerDto);

    void update(UpdateOwnerDto ownerDto);

    void delete(Long id);

    CatDto createCat(CatDto cat, Long ownerId);

    Boolean validateId(Long id, String username);
}
