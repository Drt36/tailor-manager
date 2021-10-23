package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.InventoryDto;
import com.internship.tailormanager.mapper.InventoryMapper;
import com.internship.tailormanager.model.Inventory;
import com.internship.tailormanager.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    public InventoryDto saveInventory(InventoryDto inventoryDto) {
        return inventoryMapper.modelToDto(inventoryRepository.save(inventoryMapper.dtoToModel(inventoryDto)));
    }

    public InventoryDto getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.getById(id);
        return inventoryMapper.modelToDto(inventory);
    }

    public Page<InventoryDto> getAllActiveInventory(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Inventory> inventories = inventoryRepository.findAll(pageable);

        Page<InventoryDto> inventoryDtos = inventories.map(inventory -> inventoryMapper.modelToDto(inventory));
        return inventoryDtos;
    }

    public InventoryDto updateInventory(Long id, InventoryDto inventoryDto){
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        inventory.setName(inventoryDto.getName());
        inventory.setLength(inventoryDto.getLength());

        Inventory savedInventory = inventoryRepository.save(inventory);

        return inventoryMapper.modelToDto(savedInventory);
    }

}
