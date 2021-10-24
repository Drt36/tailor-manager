package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.OrderStatus;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private LocalDate date;
    private LocalDate deliveryDate;
    private OrderStatus orderstatus;
    private Float orderAmount;
    private Float clothAmount;
    private Float discount;
    private Float advance;
    private Float totalAmount;
    private Float remainingAmount;
    private String description;
    private Boolean isBilled;
    private Status status;
    private Product product;
    private Staff staff;
    private Bill bill;
    private Customer customer;
    private Measurment measurment;
}
