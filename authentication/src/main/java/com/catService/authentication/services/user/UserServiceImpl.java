package com.catService.authentication.services.user;

import com.catService.authentication.mappers.CommandMapper;
import com.catService.authentication.mappers.UserMapper;
import com.catService.authentication.services.role.RoleService;
import com.catService.contract.dto.auth.RegisteredUserDto;
import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.contract.dto.auth.UserDto;
import com.catService.infrastructure.entities.User;
import jakarta.transaction.Transactional;
import com.catService.kafka.services.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.catService.infrastructure.repositories.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final KafkaService kafkaService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CommandMapper commandMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet())
        );
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Пользователя %s не существует", username))
        );
    }

    public RegisteredUserDto create(UserDto userDto, CreateOwnerDto ownerDto) {
        User user = userMapper.toEntity(userDto);
        user.setRoles(Set.of(roleService.getUserRole()));
        RegisteredUserDto registeredUserDto = userMapper.toRegisteredUserDto(userRepository.save(user));

        String ownerTopic = "owner";
        kafkaService.sendAsync(commandMapper.createOwner(user, ownerDto), ownerTopic);

        return registeredUserDto;
    }
}
