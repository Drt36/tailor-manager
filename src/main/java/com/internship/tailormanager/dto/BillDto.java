package com.internship.tailormanager.dto;

import com.internship.tailormanager.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private Long billId;

    private LocalDateTime date;

    private float billActualAmount;

    private float billAdvanced;

    private float billDueamount;

    private List<Order> orderList;

}
