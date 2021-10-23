package com.internship.tailormanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.internship.tailormanager.enums.ProductCategory;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.listener.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product", schema = "tms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(EntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long productId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private float cost;

    private float paymentForStaff;

    private float clothLength;

    @Column(length = 50)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 14)
    private ProductCategory productCategory;

    @Column(nullable = false)
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    @JsonBackReference(value = "backrefrence-inventory")
    private Inventory inventory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "backrefrence-product")
    private List<Order> orderList;
}
