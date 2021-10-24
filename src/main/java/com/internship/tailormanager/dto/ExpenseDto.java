package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {
    private Long expenseId;
    private String title;
    private Float amount;
    private String description;
    private LocalDateTime date;
    private Status status;
}
