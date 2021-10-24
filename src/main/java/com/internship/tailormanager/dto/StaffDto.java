package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.Gender;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.Order;
import com.internship.tailormanager.model.StaffSalary;
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
public class StaffDto {
    private Long staffId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private int age;
    private int contact;
    private String email;
    private String address;
    private Status status;
    private List<Order> orderList;
    private List<StaffSalary> staffSalaryList;
}
