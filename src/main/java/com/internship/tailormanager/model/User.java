package com.internship.tailormanager.model;

import com.internship.tailormanager.enums.Gender;
import com.internship.tailormanager.enums.Role;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.listener.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "tms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(EntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Gender gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 10)
    private String contact;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private Role role;

    @Column(nullable = false, length = 120)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private Status status;

    @Column(nullable = false)
    private String imagePath;

}
