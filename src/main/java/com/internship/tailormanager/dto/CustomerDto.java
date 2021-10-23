package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.Gender;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long customerId;

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
    @Size(min = 10,message = "Contact should be 10 digits.")
    private int contact;

    @Email
    @NotNull(message = "Email should not be empty!")
    @Size(max = 50, message = "Maximum length should be 50.")
    private String email;

    @NotEmpty(message = "Address should not be empty!")
    private String address;

    private Status status;

    private List<Order> orderList;
}
