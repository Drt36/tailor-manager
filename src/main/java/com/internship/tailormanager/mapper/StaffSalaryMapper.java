package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.StaffSalaryDto;
import com.internship.tailormanager.model.StaffSalary;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StaffSalaryMapper {
    StaffSalaryMapper STAFF_SALARY_MAPPER= Mappers.getMapper(StaffSalaryMapper.class);

    StaffSalaryDto modelToDto(StaffSalary staffSalary);

    @InheritInverseConfiguration
    StaffSalary dtoToModel(StaffSalaryDto staffSalaryDto);
}
