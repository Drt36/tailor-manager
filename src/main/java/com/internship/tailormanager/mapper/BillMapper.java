package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.BillDto;
import com.internship.tailormanager.model.Bill;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BillMapper {
    BillMapper BILL_MAPPER= Mappers.getMapper(BillMapper.class);

    BillDto modelToDto(Bill bill);

    @InheritInverseConfiguration
    Bill dtoToModel(BillDto billDto);
}
