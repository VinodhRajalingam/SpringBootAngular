package com.srv.springsecurity6.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String username;
    private String password;
    private String confirm_password;
}
