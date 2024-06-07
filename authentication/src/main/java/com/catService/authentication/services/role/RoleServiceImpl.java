package com.catService.authentication.services.role;

import com.catService.infrastructure.entities.Role;
import com.catService.contract.exceptions.auth.RoleNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.catService.infrastructure.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    final RoleRepository roleRepository;

    @Override
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").orElseThrow(
                () -> new RoleNotExistException("Роли ROLE_USER не существует")
        );
    }
}
