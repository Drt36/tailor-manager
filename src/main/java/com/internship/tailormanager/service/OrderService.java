package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.CustomerDto;
import com.internship.tailormanager.dto.OrderDto;
import com.internship.tailormanager.enums.OrderStatus;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.mapper.OrderMapper;
import com.internship.tailormanager.mapper.ProductMapper;
import com.internship.tailormanager.model.*;
import com.internship.tailormanager.repository.OrderRepository;
import com.internship.tailormanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public OrderDto saveOrder(OrderDto orderDto) {
        Order order = orderMapper.dtoToModel(orderDto);
        order.setStatus(Status.ACTIVE);
        order.setDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.NEW);
        order.setTotalAmount((orderDto.getClothAmount() + orderDto.getOrderAmount()) - orderDto.getDiscount());
        order.setRemainingAmount((orderDto.getTotalAmount() - orderDto.getAdvance()));
        return orderMapper.modelToDto(orderRepository.save(order));
    }

    private Long orderId;

    private LocalDate date;

    @NotNull
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderstatus;

    private Float orderAmount;

    private Float clothAmount;

    private Float discount;

    private Float advance;

    private Float totalAmount;

    private Float remainingAmount;

    private String description;

    private Boolean isBilled;

    private Status status;

    private Product product;

    private Staff staff;

    private Bill bill;

    private Customer customer;

    private Measurment measurment;

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.getOrderByOrderIdAndStatus(id, Status.ACTIVE);
        return orderMapper.modelToDto(order);
    }

    public Page<OrderDto> getAllActiveOrders(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Order> orders = orderRepository.findOrderByStatus(Status.ACTIVE, pageable);

        Page<OrderDto> orderDtos = orders.map(order -> orderMapper.modelToDto(order));
        return orderDtos;
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setDeliveryDate(orderDto.getDeliveryDate());
        order.setOrderAmount(orderDto.getOrderAmount());
        order.setClothAmount(orderDto.getClothAmount());
        order.setDiscount(orderDto.getDiscount());
        order.setAdvance(orderDto.getAdvance());
        order.setTotalAmount((orderDto.getClothAmount() + orderDto.getOrderAmount()) - orderDto.getDiscount());
        order.setRemainingAmount((orderDto.getTotalAmount() - orderDto.getAdvance()));
        order.setDescription(orderDto.getDescription());
        order.setStaff(orderDto.getStaff());

        Order savedOrder = orderRepository.save(order);

        //update measurment

        return orderMapper.modelToDto(savedOrder);
    }

    public OrderDto deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        order.setStatus(Status.REMOVED);
        return orderMapper.modelToDto(orderRepository.save(order));
    }

    public OrderDto updateDueAmount(Long id, Float payingAmount) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setRemainingAmount(order.getRemainingAmount() - payingAmount);

        //update bill according to bill id

        return orderMapper.modelToDto(orderRepository.save(order));
    }

    public OrderDto updateOrderStatus(Long id, OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setOrderStatus(orderStatus);
        return orderMapper.modelToDto(orderRepository.save(order));
    }

    public  OrderDto addToBill(Long id){
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setIsBilled(true);

        //add to bill logic

        return orderMapper.modelToDto(orderRepository.save(order));
    }


}
