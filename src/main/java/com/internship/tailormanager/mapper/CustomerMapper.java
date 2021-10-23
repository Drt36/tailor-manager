package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.CustomerDto;
import com.internship.tailormanager.model.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper CUSTOMER_MAPPER= Mappers.getMapper(CustomerMapper.class);
    CustomerDto modelToDto(Customer customer);

    @InheritInverseConfiguration
    Customer dtoToModel(CustomerDto customerDto);

}
