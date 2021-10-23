package com.internship.tailormanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.internship.tailormanager.enums.Gender;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.listener.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "customer", schema = "tms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(EntityListener.class)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Gender gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length =10)
    private int contact;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private Status status;

    @Email
    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length =100)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "backrefrence-customer")
    private List<Order> orderList;
}
