package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.ExpenseDto;
import com.internship.tailormanager.model.Expense;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseMapper EXPENSE_MAPPER= Mappers.getMapper(ExpenseMapper.class);

    ExpenseDto modelToDto(Expense expense);

    @InheritInverseConfiguration
    Expense dtoToModel(ExpenseDto expenseDto);
}
