package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.ProductCategory;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.Inventory;
import com.internship.tailormanager.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productId;
    private String name;
    private float cost;
    private float paymentForStaff;
    private float clothLength;
    private String description;
    private ProductCategory product_category;
    private String imagePath;
    private Status status;
    private Inventory inventory;
    private List<Order> orderList;
}
