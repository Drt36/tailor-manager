package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.MeasurmentDto;
import com.internship.tailormanager.model.Measurment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MeasurmentMapper {
    MeasurmentMapper MEASURMENT_MAPPER= Mappers.getMapper(MeasurmentMapper.class);

    MeasurmentDto modelToDto(Measurment measurment);

    @InheritInverseConfiguration
    Measurment dtoToModel(MeasurmentDto measurmentDto);
}
