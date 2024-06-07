package com.catService.owner.kafkaListeners;

import com.catService.contract.commands.DeleteById;
import com.catService.contract.commands.GetAll;
import com.catService.contract.commands.GetById;
import com.catService.contract.commands.owner.CreateCat;
import com.catService.contract.commands.owner.CreateOwner;
import com.catService.contract.commands.owner.IsCatOwnedBy;
import com.catService.contract.commands.owner.UpdateOwner;
import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.owner.OwnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import com.catService.owner.services.OwnerService;

import java.util.List;

@Service
@RequiredArgsConstructor
@KafkaListener(topics = "owner", groupId = "owner-group")
public class OwnerListener {
    private final OwnerService ownerService;

    @KafkaHandler
    @SendTo
    public OwnerDto getById(GetById command) {
        return ownerService.getById(command.Id());
    }

    @KafkaHandler
    @SendTo
    public List<OwnerDto> getAll(GetAll command) {
        return ownerService.getAll();
    }

    @KafkaHandler
    @SendTo
    public Boolean isCatOwnedBy(IsCatOwnedBy command) {
        return ownerService.isCatOwnedBy(command.catId(), command.username());
    }

    @KafkaHandler
    @SendTo
    public OwnerDto create(CreateOwner command) {
        return ownerService.create(command.user(), command.ownerDto());
    }

    @KafkaHandler
    public void update(UpdateOwner command) {
        ownerService.update(command.ownerDto());
    }

    @KafkaHandler
    public void delete(DeleteById command) {
        ownerService.delete(command.id());
    }

    @KafkaHandler
    @SendTo
    public CatDto createCat(CreateCat command) {
        return ownerService.createCat(command.catDto(), command.ownerId());
    }
}
