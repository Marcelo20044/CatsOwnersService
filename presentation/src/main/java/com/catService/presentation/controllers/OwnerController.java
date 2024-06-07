package com.catService.presentation.controllers;


import com.catService.presentation.commandMappers.CommonCommandMapper;
import com.catService.presentation.commandMappers.OwnerCommandMapper;
import com.catService.contract.dto.cat.CatDto;
import com.catService.contract.dto.owner.OwnerDto;
import com.catService.contract.dto.owner.UpdateOwnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.catService.kafka.services.KafkaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final KafkaService kafkaService;
    private final OwnerCommandMapper ownerCommandMapper;
    private final CommonCommandMapper commonCommandMapper;
    
    private final String TOPIC = "owner";

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(kafkaService.sendSync(
                commonCommandMapper.getById(id),
                TOPIC,
                OwnerDto.class
        ));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(kafkaService.sendSync(
                commonCommandMapper.getAll(),
                TOPIC,
                List.class
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateOwnerDto ownerDto) {
        if (!id.equals(ownerDto.id())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        kafkaService.sendAsync(ownerCommandMapper.updateOwner(ownerDto), TOPIC);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        kafkaService.sendAsync(commonCommandMapper.deleteById(id), TOPIC);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{ownerId}/cat")
    public ResponseEntity<CatDto> createCat(@RequestBody CatDto catDto, @PathVariable Long ownerId) {
        CatDto cat = kafkaService.sendSync(
                ownerCommandMapper.createCat(catDto, ownerId), 
                TOPIC,
                CatDto.class
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(cat);
    }
}
