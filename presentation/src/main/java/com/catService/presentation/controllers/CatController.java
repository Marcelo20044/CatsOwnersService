package com.catService.presentation.controllers;

import com.catService.presentation.commandMappers.CatCommandMapper;
import com.catService.presentation.commandMappers.CommonCommandMapper;
import com.catService.presentation.commandMappers.OwnerCommandMapper;
import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.cat.CatWithFriendsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.catService.kafka.services.KafkaService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cat")
public class CatController {
    private final KafkaService kafkaService;
    private final CatCommandMapper catCommandMapper;
    private final OwnerCommandMapper ownerCommandMapper;
    private final CommonCommandMapper commonCommandMapper;

    private final String TOPIC = "cat";


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @catController.isCatOwnedBy(#id, authentication.name)")
    public ResponseEntity<CatWithFriendsDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(kafkaService.sendSync(
                commonCommandMapper.getById(id),
                TOPIC,
                CatWithFriendsDto.class
        ));
    }

    @GetMapping("/")
    public ResponseEntity<List<CatWithFriendsDto>> getAll(Principal principal, Authentication authentication) {
        List<CatWithFriendsDto> cats = kafkaService.sendSync(
                commonCommandMapper.getAll(),
                TOPIC,
                List.class
        );

        String adminRole = "ROLE_ADMIN";
        return ResponseEntity.ok(cats.stream()
                .filter(cat ->
                        this.isCatOwnedBy(cat.id(), principal.getName()) ||
                        authentication.getAuthorities().stream()
                        .anyMatch(role -> role.getAuthority().equals(adminRole)))
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @catController.isCatOwnedBy(#id, authentication.name)")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CatDto catDto) {
        if (!id.equals(catDto.id())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        kafkaService.sendAsync(catCommandMapper.updateCat(catDto), TOPIC);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @catController.isCatOwnedBy(#id, authentication.name)")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        kafkaService.sendAsync(commonCommandMapper.deleteById(id), TOPIC);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createFriend/{id}/{friendId}")
    @PreAuthorize("hasAnyRole('ADMIN') or @catController.isCatOwnedBy(#id, authentication.name)")
    public ResponseEntity<Void> createFriend(@PathVariable Long id, @PathVariable Long friendId) {
        kafkaService.sendAsync(catCommandMapper.createCatFriend(id, friendId), TOPIC);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public Boolean isCatOwnedBy(Long id, String username) {
        String ownerTopic = "owner";
        return kafkaService.sendSync(
                ownerCommandMapper.isCatOwnedBy(id, username),
                ownerTopic,
                Boolean.class);
    }
}
