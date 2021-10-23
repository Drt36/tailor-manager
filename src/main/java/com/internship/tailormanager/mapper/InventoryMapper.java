package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.InventoryDto;
import com.internship.tailormanager.model.Inventory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryMapper INVENTORY_MAPPER= Mappers.getMapper(InventoryMapper.class);

    InventoryDto modelToDto(Inventory inventory);

    @InheritInverseConfiguration
    Inventory dtoToModel(InventoryDto inventoryDto);

}
