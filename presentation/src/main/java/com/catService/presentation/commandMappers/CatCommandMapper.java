package com.catService.presentation.commandMappers;

import com.catService.contract.commands.cat.CreateCatFriend;
import com.catService.contract.commands.cat.UpdateCat;
import com.catService.contract.dto.cat.CatDto;
import org.springframework.stereotype.Component;

@Component
public class CatCommandMapper {
    public UpdateCat updateCat(CatDto catDto) {
        return new UpdateCat(catDto);
    }

    public CreateCatFriend createCatFriend(Long id, Long friendId) {
        return new CreateCatFriend(id, friendId);
    }
}
