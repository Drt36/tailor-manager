package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.Gender;
import com.internship.tailormanager.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDto {
    @NotEmpty(message = "First Name Should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50 digits.")
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50 digits.")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender Should not be null.")
    private Gender gender;

    @NotNull(message = "Age should not be empty!")
    private int age;

    @NotEmpty(message = "Contact should not be empty!")
    @Size(min = 10,max=10, message = "Contact should be 10 digits.")
    private String contact;

    @Email
    @NotEmpty(message = "Email should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50 digits.")
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role Should not be null.")
    private Role role;
}
