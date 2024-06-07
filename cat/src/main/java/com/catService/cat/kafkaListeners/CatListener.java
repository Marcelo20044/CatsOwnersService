package com.catService.cat.kafkaListeners;

import com.catService.cat.services.CatService;
import com.catService.contract.commands.DeleteById;
import com.catService.contract.commands.GetAll;
import com.catService.contract.commands.GetById;
import com.catService.contract.commands.cat.CreateCatFriend;
import com.catService.contract.commands.cat.CreateCatWithOwner;
import com.catService.contract.commands.cat.IsOwnedByWithOwner;
import com.catService.contract.commands.cat.UpdateCat;
import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.cat.CatWithFriendsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@KafkaListener(topics = "cat", groupId = "cat-group")
public class CatListener {
    private final CatService catService;

    @KafkaHandler
    @SendTo
    public CatWithFriendsDto getById(GetById command) {
        return catService.getById(command.Id());
    }

    @KafkaHandler
    @SendTo
    public List<CatWithFriendsDto> getAll(GetAll command) {
        return catService.getAll();
    }

    @KafkaHandler
    @SendTo
    public Boolean isOwnedBy(IsOwnedByWithOwner command) {
        return catService.isOwnedBy(command.id(), command.owner());
    }

    @KafkaHandler
    @SendTo
    public CatDto create(CreateCatWithOwner command) {
        return catService.create(command.catDto(), command.owner());
    }

    @KafkaHandler
    public void update(UpdateCat command) {
        catService.update(command.catDto());
    }

    @KafkaHandler
    public void delete(DeleteById command) {
        catService.delete(command.id());
    }

    @KafkaHandler
    @SendTo
    public void createFriend(CreateCatFriend command) {
        catService.createFriend(command.id(), command.friendId());
    }
}
