package com.srv.springsecurity6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AuthProvider {

    @Bean public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired SecurityConfig userDetailService;
    @Bean
    public DaoAuthenticationProvider daoAuth() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

  /*  @Bean
    public UserDetailsService inMemory() {

        UserDetails userAdmin = User.builder()
                .username("rajalingam123")
                .password("{bcrypt}$2a$12$jwQjKBCUF1a1IFd9lZHmfOWNLWHZiVT97qTuscqEypcmXjafJLjGS")
                .roles("ADMIN")
                .build();

        UserDetails userUser = User.builder()
                .username("savithri123")
                .password("{bcrypt}$2a$12$9F6uN2THlirUAejaGAYZNuL8ogzRrOMDhI.iARJk5o/HrwmleGXwK")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userAdmin,userUser);
    }*/
}
