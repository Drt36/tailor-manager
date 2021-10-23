package com.internship.tailormanager.repository;

import com.internship.tailormanager.model.Measurment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurmentRepository extends JpaRepository<Measurment,Long> {
}
