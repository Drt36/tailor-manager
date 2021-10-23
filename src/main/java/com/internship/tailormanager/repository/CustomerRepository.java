package com.internship.tailormanager.repository;

import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer getCustomerByEmail(String email);

    Customer getCustomerByEmailAndStatus(String email, Status status);

    Page<Customer> findCustomerByStatus(Status status, Pageable pageable);

    Boolean existsCustomerByEmailAndStatus(String email,Status status);
}
