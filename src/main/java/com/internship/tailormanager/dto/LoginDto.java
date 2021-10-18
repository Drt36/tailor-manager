package com.internship.tailormanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @Email
    @NotNull(message = "Email should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50.")
    private String email;

    @NotNull(message = "Password should not be empty!")
    private String password;
}
