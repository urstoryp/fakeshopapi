package com.example.fakeshopapi.security.jwt.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LoginUserDto {
    private String email;
    private List<String> roles = new ArrayList<>();

    public void addRole(String role){
        roles.add(role);
    }
}
