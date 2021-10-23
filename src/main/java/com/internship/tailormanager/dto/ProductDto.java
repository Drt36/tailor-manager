package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.ProductCategory;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.Inventory;
import com.internship.tailormanager.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productId;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @Column(nullable = false)
    private float cost;

    private float paymentForStaff;

    private float clothLength;

    @Size(max = 50)
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductCategory product_category;

    @NotEmpty
    private String imagePath;

    private Status status;

    private Inventory inventory;

    private List<Order> orderList;
}
