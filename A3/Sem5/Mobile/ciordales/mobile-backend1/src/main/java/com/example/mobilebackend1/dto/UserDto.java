package com.example.mobilebackend1.dto;

import com.example.mobilebackend1.model.User;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
