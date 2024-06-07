package com.catService.cat.services;

import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.cat.CatWithFriendsDto;
import com.catService.infrastructure.entities.Owner;

import java.util.List;

public interface CatService {
    CatWithFriendsDto getById(Long id);

    List<CatWithFriendsDto> getAll();

    Boolean isOwnedBy(Long id, Owner owner);

    CatDto create(CatDto cat, Owner owner);

    void update(CatDto catDto);

    void delete(Long id);

    void createFriend(Long id, Long friendId);
}
