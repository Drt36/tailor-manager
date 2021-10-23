package com.internship.tailormanager.dto;

import com.internship.tailormanager.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private int id;

    @NotEmpty
    @Size(max = 50)
    private String name;

    private Float length;

    private List<Product> productList;
}
