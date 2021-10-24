package com.internship.tailormanager.repository;

import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.Customer;
import com.internship.tailormanager.model.Expense;
import com.internship.tailormanager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductByProductIdAndStatus(Long id, Status status);

    Page<Product> findProductByStatus(Status status, Pageable pageable);
}
