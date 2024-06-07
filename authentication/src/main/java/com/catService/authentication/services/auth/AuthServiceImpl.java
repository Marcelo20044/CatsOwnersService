package com.catService.authentication.services.auth;

import com.catService.authentication.services.user.UserService;
import com.catService.contract.dto.owner.CreateOwnerDto;
import com.catService.contract.dto.auth.RegisteredUserDto;
import com.catService.contract.dto.auth.TokenDto;
import com.catService.contract.dto.auth.UserDto;
import com.catService.contract.exceptions.auth.InvalidAuthDataException;
import com.catService.contract.exceptions.auth.UserExistException;
import com.catService.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public TokenDto createToken(UserDto userDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password())
            );
        } catch (BadCredentialsException e) {
            throw new InvalidAuthDataException("Неправильный логин или пароль");
        }

        UserDetails userDetails = userService.loadUserByUsername(userDto.username());
        return new TokenDto(jwtUtils.generateToken(userDetails));
    }

    public RegisteredUserDto createUser(UserDto userDto, CreateOwnerDto ownerDto) {
        try {
            userService.getByUsername(userDto.username());
        } catch (UsernameNotFoundException e) {
            return userService.create(userDto, ownerDto);
        }
        throw new UserExistException("Пользователь с указанным именем уже существует");
    }
}
