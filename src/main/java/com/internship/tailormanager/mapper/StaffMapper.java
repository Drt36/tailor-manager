package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.StaffDto;
import com.internship.tailormanager.model.Staff;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffMapper STAFF_MAPPER = Mappers.getMapper(StaffMapper.class);

    StaffDto modelToDto(Staff staff);

    @InheritInverseConfiguration
    Staff dtoToModel(StaffDto staffDto);
}
