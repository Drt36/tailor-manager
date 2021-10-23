package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.*;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.mapper.ProductMapper;
import com.internship.tailormanager.model.*;
import com.internship.tailormanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductDto saveProduct(ProductDto productDto, MultipartFile file) throws IOException {

        UUID uuid = UUID.randomUUID();
        File saveFile = new ClassPathResource("static/productimage").getFile();
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator+ uuid + file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Product newProduct = productMapper.dtoToModel(productDto);

        newProduct.setStatus(Status.ACTIVE);
        newProduct.setImagePath(uuid + file.getOriginalFilename());

        Product savedProduct = productRepository.save(newProduct);

        ProductDto savedProductDto = productMapper.modelToDto(savedProduct);
        savedProductDto.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productimage/").path(savedProductDto.getImagePath()).toUriString());
        return savedProductDto;
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.getProductByProductIdAndStatus(id,Status.ACTIVE);
        product.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productimage/").path(product.getImagePath()).toUriString());
        return productMapper.modelToDto(product);
    }

    public Page<ProductDto> getAllActiveProduct(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Product> products = productRepository.findProductByStatus(Status.ACTIVE, pageable);

        for (Product product : products) {
            product.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productimage/").path(product.getImagePath()).toUriString());
        }

        Page<ProductDto> productDtos = products.map(product -> productMapper.modelToDto(product));
        return productDtos;
    }

    public ProductDto updateProduct(Long id, ProductDto productDto, MultipartFile file) throws IOException {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (!file.isEmpty()) {
            File saveFile = new ClassPathResource("static/productimage").getFile();
            Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + product.getImagePath());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }

        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        product.setPaymentForStaff(productDto.getCost());
        product.setClothLength(productDto.getClothLength());
        product.setDescription(productDto.getDescription());
        product.setProductCategory(productDto.getProduct_category());
        product.setInventory(productDto.getInventory());

        Product savedProduct = productRepository.save(product);
        ProductDto savedProductDto = productMapper.modelToDto(savedProduct);
        savedProductDto.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productimage/").path(savedProductDto.getImagePath()).toUriString());
        return savedProductDto;
    }

    public ProductDto deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        product.setStatus(Status.REMOVED);

        return productMapper.modelToDto(productRepository.save(product));
    }
}
