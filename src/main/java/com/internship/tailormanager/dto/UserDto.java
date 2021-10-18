package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.Gender;
import com.internship.tailormanager.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userId;

    @NotEmpty(message = "First Name Should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50.")
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50.")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Age should not be empty!")
    private int age;

    @NotNull(message = "Contact should not be empty!")
    @Size(min = 10, message = "Contact should be 10 digits.")
    private Long contact;

    @Email
    @NotNull(message = "Email should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50.")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "Password should not be empty!")
    private String password;
}
