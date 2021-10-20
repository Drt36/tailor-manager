package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.Gender;
import com.internship.tailormanager.enums.Role;
import com.internship.tailormanager.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private int age;
    private String contact;
    private String email;
    private Role role;
    private Status status;
    private String imagePath;
}
