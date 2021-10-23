package com.internship.tailormanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.internship.tailormanager.enums.OrderStatus;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.listener.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders", schema = "tms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(EntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Float orderAmount;

    private Float clothAmount;

    private Float discount;

    private Float advance;

    private Float totalAmount;

    private Float remainingAmount;

    private String description;

    private Boolean isBilled;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "backrefrence-product")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    @JsonBackReference(value = "backrefrence-staff")
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    @JsonBackReference(value = "backrefrence-bill")
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference(value = "backrefrence-customer")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurment_id")
    @JsonBackReference(value = "backrefrence-measurment")
    private Measurment measurment;
}
