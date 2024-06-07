package com.catService.presentation.commandMappers;

import com.catService.contract.commands.DeleteById;
import com.catService.contract.commands.GetAll;
import com.catService.contract.commands.GetById;
import org.springframework.stereotype.Component;

@Component
public class CommonCommandMapper {
    public GetById getById(Long id) {
        return new GetById(id);
    }

    public GetAll getAll() {
        return new GetAll();
    }

    public DeleteById deleteById(Long id) {
        return new DeleteById(id);
    }
}
