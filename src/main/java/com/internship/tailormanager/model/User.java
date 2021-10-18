package com.internship.tailormanager.model;

import com.internship.tailormanager.enums.Gender;
import com.internship.tailormanager.enums.Role;
import com.internship.tailormanager.listener.UserListener;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "tms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(UserListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "First Name Should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50.")
    private String firstName;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Last Name should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50.")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Gender gender;

    @NotNull(message = "Age should not be empty!")
    private int age;

    @NotNull(message = "Contact should not be empty!")
    @Column(unique = true, nullable = false, length = 10)
    @Size(min = 10, message = "Contact should be 10 digits.")
    private Long contact;

    @Email
    @NotNull(message = "Email should not be empty!")
    @Column(unique = true, nullable = false, length = 50)
    @Size(max = 50, message = "Maximum length should be 50.")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private Role role;

    @Column(nullable = false, length = 120)
    @NotNull(message = "Password should not be empty!")
    private String password;

}
