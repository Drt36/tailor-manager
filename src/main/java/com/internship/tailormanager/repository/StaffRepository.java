package com.internship.tailormanager.repository;

import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.Customer;
import com.internship.tailormanager.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Long> {

    Staff getStaffByEmailAndStatus(String email, Status status);
    Page<Staff> findStaffByStatus(Status status, Pageable pageable);
}
