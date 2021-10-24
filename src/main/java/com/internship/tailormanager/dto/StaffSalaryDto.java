package com.internship.tailormanager.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.internship.tailormanager.model.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffSalaryDto {
    private Long salaryId;
    private LocalDateTime date;
    private Float amount;
    private Staff staff;
}
