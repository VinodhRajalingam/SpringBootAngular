package com.srv.springsecurity6.controller;

import com.srv.springsecurity6.repository.MyUserRepository;
import com.srv.springsecurity6.dto.RegisterUserRequest;
import com.srv.springsecurity6.entity.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class UserController {

    @Autowired
    MyUserRepository myUserRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Value("${spring.security.user.password}")
    private String password;

    @GetMapping(value = {"/"})
    private ModelAndView landingPage() {
        return new ModelAndView("login.html");
    }

    @GetMapping(value = {"/loginFailure"})
    private String loginFailure() {
        return "Login Failure !!";
    }
    @GetMapping(value = {"/redirect"})
    private String redirectOauth() {
        return "Login Success !!";
    }
    @PostMapping("/newUserRegister")
    public String newUser(RegisterUserRequest registerUserRequest) {
        MyUser myUser = new MyUser();
        myUser.setUsername(registerUserRequest.getUsername());
        myUser.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        myUser.setAccountNonExpired(true);
        myUser.setEnabled(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setAuthority("ROLE_USER");
        myUserRepository.save(myUser);
        return "User Registered Successfully!!";
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        log.info("Delete "+id);
        myUserRepository.deleteById(id);
        return "User Deleted Successfully!!";
    }
}
