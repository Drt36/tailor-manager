package com.internship.tailormanager.dto;

import com.internship.tailormanager.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurmentDto {
    private Long measurmentId;
    private Float length;
    private Float waist;
    private Float shoulder;
    private Float hand;
    private Float neck;
    private Float chest;
    private Float thigh;
    private Float innerLenght;
    private Float hip;
    private Order order;
}
