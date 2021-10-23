package com.internship.tailormanager.repository;

import com.internship.tailormanager.model.Staff;
import com.internship.tailormanager.model.StaffSalary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffSalaryRepository extends JpaRepository<StaffSalary,Long> {

    @Query("select s from StaffSalary s where s.staff.staffId=:staffId")
    Page<StaffSalary> findStaffSalaryByStaffId(@Param("staffId") Long staffId, Pageable pageable);
}
