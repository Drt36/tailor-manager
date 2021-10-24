package com.internship.tailormanager.controller;

import com.internship.tailormanager.dto.InventoryDto;
import com.internship.tailormanager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping( "/api/user/inventory")
    public ResponseEntity<InventoryDto> saveInventory(@Valid @RequestBody InventoryDto inventoryDto) {
        InventoryDto savedInventoryDto = inventoryService.saveInventory(inventoryDto);
        return new ResponseEntity<InventoryDto>(savedInventoryDto, HttpStatus.OK);
    }

    @GetMapping("/api/user/inventory/{id}")
    public InventoryDto getInventory(@NotNull @PathVariable("id") Long id) {
        return inventoryService.getInventoryById(id);
    }

    @GetMapping("/api/user/inventory")
    public Page<InventoryDto> getAllInventory(@RequestParam("page") int page) {
        Page<InventoryDto> inventoryDtos = inventoryService.getAllActiveInventory(page);
        return inventoryDtos;
    }

    @Transactional
    @PutMapping("/api/user/inventory/{id}")
    public ResponseEntity<InventoryDto> updateInventory(@NotNull @PathVariable("id") Long id, @RequestBody InventoryDto inventoryDto) {
        InventoryDto savedInventoryDto = inventoryService.updateInventory(id, inventoryDto);
        return new ResponseEntity<InventoryDto>(savedInventoryDto, HttpStatus.OK);
    }
}