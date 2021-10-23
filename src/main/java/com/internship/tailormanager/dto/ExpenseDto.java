package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {

    private Long expenseId;

    @NotEmpty
    @Size(max = 50)
    private String title;

    @NotNull
    private Float amount;

    private String description;

    @NotNull
    private LocalDateTime date;

    private Status status;
}
