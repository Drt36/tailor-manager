package com.internship.tailormanager.repository;

import com.internship.tailormanager.model.Staff;
import com.internship.tailormanager.model.StaffSalary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffSalaryRepository extends JpaRepository<StaffSalary, Long> {
    Page<StaffSalary> findStaffSalaryByStaff(Staff staff,Pageable pageable);
}
