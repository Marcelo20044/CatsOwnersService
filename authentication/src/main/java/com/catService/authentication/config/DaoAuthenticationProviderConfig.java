package com.catService.authentication.config;

import com.catService.authentication.services.user.UserService;
import com.catService.security.config.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@RequiredArgsConstructor
@Configuration
public class DaoAuthenticationProviderConfig {
    private final UserService userService;
    private final PasswordEncoderConfig passwordEncoderConfig;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoderConfig.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
