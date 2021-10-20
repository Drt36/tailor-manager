package com.internship.tailormanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {

    @Email
    @NotNull(message = "Email should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50.")
    private String email;

    @NotNull(message = "Password should not be empty!")
    @Size(min=8,message = "Password should be minimum 8 digits.")
    private String password;
}
