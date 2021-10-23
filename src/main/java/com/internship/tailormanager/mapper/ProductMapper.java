package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.ProductDto;
import com.internship.tailormanager.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER= Mappers.getMapper(ProductMapper.class);

    ProductDto modelToDto(Product product);

    @InheritInverseConfiguration
    Product dtoToModel(ProductDto productDto);

}
