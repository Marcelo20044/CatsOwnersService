package com.catService.owner.services;

import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.contract.exceptions.owner.OwnerNotExistException;
import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.owner.OwnerDto;
import com.catService.contract.dto.owner.UpdateOwnerDto;
import com.catService.infrastructure.entities.Owner;
import com.catService.infrastructure.entities.User;
import com.catService.kafka.services.KafkaService;
import lombok.RequiredArgsConstructor;
import com.catService.owner.mappers.CommandMapper;
import com.catService.owner.mappers.OwnerMapper;
import org.springframework.stereotype.Service;
import com.catService.infrastructure.repositories.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerMapper mapper;
    private final CommandMapper commandMapper;
    private final OwnerRepository ownerRepository;
    private final KafkaService kafkaService;

    private final String CAT_TOPIC = "cat";

    @Override
    public OwnerDto getById(Long id) {
        return mapper.toDto(ownerRepository.findById(id).orElseThrow(
                () -> new OwnerNotExistException(String.format("Хозяина с айди %d не существует", id))
        ));
    }

    @Override
    public List<OwnerDto> getAll() {
        List<OwnerDto> cats = ownerRepository.findAll().stream().map(mapper::toDto).toList();
        if (cats.isEmpty()) throw new OwnerNotExistException("Ни одного хозяина не найдено");
        return cats;
    }

    @Override
    public Boolean isCatOwnedBy(Long catId, String username) {
        Owner owner = ownerRepository.findByUsername(username).orElseThrow(
                () -> new OwnerNotExistException(String.format("Хозяина %s не существует", username))
        );
        return kafkaService.sendSync(
                commandMapper.isOwnedByWithOwner(catId, owner),
                CAT_TOPIC,
                Boolean.class
        );
    }

    @Override
    public OwnerDto create(User user, CreateOwnerDto ownerDto) {
        Owner owner = mapper.toEntity(ownerDto, user);
        return mapper.toDto(ownerRepository.save(owner));
    }

    @Override
    public void update(UpdateOwnerDto ownerDto) {
        Optional<Owner> optionalOwner = ownerRepository.findById(ownerDto.id());
        if (optionalOwner.isEmpty())
            throw new OwnerNotExistException(String.format("Хозяина с айди %d не существует", ownerDto.id()));

        Owner owner = optionalOwner.get();
        Owner updateOwner = mapper.toEntity(ownerDto);
        updateOwner.setCats(owner.getCats());
        updateOwner.setUser(owner.getUser());
        ownerRepository.save(updateOwner);
    }

    @Override
    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public CatDto createCat(CatDto cat, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(
                () -> new OwnerNotExistException(String.format("Хозяина с айди %d не существует", ownerId))
        );
        return kafkaService.sendSync(
                commandMapper.createCatWithOwner(cat, owner),
                CAT_TOPIC,
                CatDto.class
        );
    }
}
