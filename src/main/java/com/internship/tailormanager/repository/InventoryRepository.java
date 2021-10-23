package com.internship.tailormanager.repository;

import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Page<Inventory> findAll(Pageable pageable);
}
