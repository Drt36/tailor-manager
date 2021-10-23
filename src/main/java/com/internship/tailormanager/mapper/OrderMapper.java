package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.OrderDto;
import com.internship.tailormanager.model.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper ORDER_MAPPER= Mappers.getMapper(OrderMapper.class);

    OrderDto modelToDto(Order order);

    @InheritInverseConfiguration
    Order dtoToModel(OrderDto orderDto);
}
