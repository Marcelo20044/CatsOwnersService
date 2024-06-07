package com.catService.cat.services;

import com.catService.cat.mappers.CatMapper;
import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.cat.CatWithFriendsDto;
import com.catService.infrastructure.entities.Cat;
import com.catService.infrastructure.entities.Owner;
import com.catService.contract.exceptions.cat.CatAndFriendSameException;
import com.catService.contract.exceptions.cat.CatNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.catService.infrastructure.repositories.CatRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository repository;
    private final CatMapper mapper;

    @Override
    public CatWithFriendsDto getById(Long id) {
        return mapper.toDtoWithFriends(repository.findById(id).orElseThrow(
                () -> new CatNotExistException(String.format("Кота с айди %d не существует", id))
        ));
    }

    @Override
    public List<CatWithFriendsDto> getAll() {
        List<CatWithFriendsDto> cats = repository.findAll().stream().map(mapper::toDtoWithFriends).toList();
        if (cats.isEmpty()) throw new CatNotExistException("Ни одного кота не найдено");
        return cats;
    }

    public Boolean isOwnedBy(Long id, Owner owner) {
        return repository.findById(id)
                .filter(value -> repository.findByOwner(owner).contains(value))
                .isPresent();
    }

    @Override
    public CatDto create(CatDto cat, Owner owner) {
        Cat catEntity = mapper.toEntity(cat);
        catEntity.setOwner(owner);
        return mapper.toDto(repository.save(catEntity));
    }

    @Override
    public void update(CatDto catDto) {
        Optional<Cat> optionalCat = repository.findById(catDto.id());
        if (optionalCat.isEmpty())
            throw new CatNotExistException(String.format("Кота с айди %d не существует", catDto.id()));

        Cat cat = optionalCat.get();
        Cat updateCat = mapper.toEntity(catDto);
        updateCat.setOwner(cat.getOwner());
        updateCat.setFriends(cat.getFriends());
        repository.save(updateCat);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void createFriend(Long id, Long friendId) {
        Cat cat = repository.findById(id).orElseThrow(
                () -> new CatNotExistException(String.format("Кота с айди %d не существует", id))
        );
        Cat friendCat = repository.findById(friendId).orElseThrow(
                () -> new CatNotExistException(String.format("Кота с айди %d не существует", friendId))
        );

        if (cat.equals(friendCat))
            throw new CatAndFriendSameException("Нельзя сделать кота другом для него самого, если он не шиз конечно");

        cat.getFriends().add(friendCat);
        friendCat.getFriends().add(cat);
        repository.save(cat);
        repository.save(friendCat);
    }
}
