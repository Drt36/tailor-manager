package com.internship.tailormanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.tailormanager.dto.InventoryDto;
import com.internship.tailormanager.dto.ProductDto;
import com.internship.tailormanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/api/user/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDto> saveUser(@RequestParam("product") String product, @RequestParam("inventory") String inventory, @RequestParam("file") MultipartFile file) throws IOException {
        ProductDto productPostDto = new ObjectMapper().readValue(product, ProductDto.class);
        InventoryDto inventoryDto = new ObjectMapper().readValue(inventory, InventoryDto.class);

        ProductDto savedProductDto = productService.saveProduct(productPostDto, inventoryDto, file);

        return new ResponseEntity<ProductDto>(savedProductDto, HttpStatus.OK);
    }

    @GetMapping("/api/admin/product/{id}")
    public ProductDto getProductById(@NotNull @PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/api/admin/product")
    public Page<ProductDto> getAllProducts(@RequestParam("page") int page) {
        Page<ProductDto> productDtos = productService.getAllActiveProduct(page);
        return productDtos;
    }

    @Transactional
    @PutMapping("/api/admin/product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@NotNull @PathVariable("id") Long id,
                                                    @RequestParam("product") String product,
                                                    @RequestParam("inventory") String inventory,
                                                    @NotNull @RequestParam("file") MultipartFile file) throws IOException {

        ProductDto productDto = new ObjectMapper().readValue(product, ProductDto.class);
        InventoryDto inventoryDto = new ObjectMapper().readValue(inventory, InventoryDto.class);

        ProductDto savedProductDto = productService.updateProduct(id, productDto, inventoryDto, file);
        return new ResponseEntity<ProductDto>(savedProductDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/admin/product/delete/{id}")
    public ResponseEntity<ProductDto> deleteUser(@NotNull @PathVariable("id") Long id) {
        ProductDto productDto = productService.deleteProduct(id);
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }
}
