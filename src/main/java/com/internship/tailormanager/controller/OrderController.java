package com.internship.tailormanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.tailormanager.dto.*;
import com.internship.tailormanager.enums.OrderStatus;
import com.internship.tailormanager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/api/user/order/{id}")
    public OrderDto getOrder(@NotNull @PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/api/user/order")
    public Page<OrderDto> getAllOrder(@RequestParam("page") int page) {
        Page<OrderDto> orderDtos = orderService.getAllActiveOrders(page);
        return orderDtos;
    }

    @GetMapping("/api/user/order/customer/{email}")
    public Page<OrderDto> getAllOrderByCustomer(@RequestParam("page") int page, @NotNull @RequestParam("email") String email) {
        Page<OrderDto> orderDtos = orderService.getAllActiveOrdersByCustomer(page, email);
        return orderDtos;
    }

    @PostMapping(value = "/api/user/order")
    public ResponseEntity<OrderDto> saveOrder(@RequestParam("order") String order,
                                              @RequestParam("measurment") String measurment,
                                              @RequestParam("customer") String customer,
                                              @RequestParam("product") String product,
                                              @RequestParam("staff") String staff) throws JsonProcessingException {

        OrderDto orderPostDto = new ObjectMapper().readValue(order, OrderDto.class);
        MeasurmentDto measurmentPostDto = new ObjectMapper().readValue(measurment, MeasurmentDto.class);
        CustomerDto customerDto = new ObjectMapper().readValue(customer, CustomerDto.class);
        ProductDto productDto = new ObjectMapper().readValue(product, ProductDto.class);
        StaffDto staffDto = new ObjectMapper().readValue(staff, StaffDto.class);

        OrderDto savedOrderDto = orderService.saveOrder(orderPostDto, measurmentPostDto, customerDto, productDto, staffDto);
        return new ResponseEntity<OrderDto>(savedOrderDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/user/order/{id}")
    public ResponseEntity<OrderDto> updateOrder(@NotNull @PathVariable("id") Long id,
                                                @RequestParam("order") String order,
                                                @RequestParam("measurment") String measurment,
                                                @RequestParam("staff") String staff) throws JsonProcessingException {
        OrderDto orderUpdateDto = new ObjectMapper().readValue(order, OrderDto.class);
        MeasurmentDto measurmentUpdateDto = new ObjectMapper().readValue(measurment, MeasurmentDto.class);
        StaffDto staffUpdateDto = new ObjectMapper().readValue(staff, StaffDto.class);

        OrderDto updatedOrderDto = orderService.updateOrder(id, orderUpdateDto, measurmentUpdateDto, staffUpdateDto);

        return new ResponseEntity<OrderDto>(updatedOrderDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/user/order/updatedue/{id}")
    public ResponseEntity<OrderDto> updateDueAmount(@NotNull @PathVariable("id") Long id, @NotNull @RequestParam("paidamount") Float paidAmount) {
        OrderDto updatedOrderDto = orderService.updateDueAmount(id, paidAmount);
        return new ResponseEntity<OrderDto>(updatedOrderDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/user/order/updateorderstatus/{id}")
    public ResponseEntity<OrderDto> updateOrderStatus(@NotNull @PathVariable("id") Long id, @NotNull @RequestParam("orderstatus") OrderStatus orderStatus) {
        OrderDto updatedOrderDto = orderService.updateOrderStatus(id, orderStatus);
        return new ResponseEntity<OrderDto>(updatedOrderDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/user/order/delete/{id}")
    public ResponseEntity<OrderDto> deleteExpense(@NotNull @PathVariable("id") Long id) {
        OrderDto orderDto = orderService.deleteOrder(id);
        return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/user/order/addbill/{email}")
    public ResponseEntity<BillDto> updateOrder(@NotNull @PathVariable("email") String customerEmail, @RequestParam("page") int page) {
        BillDto bill = orderService.addToBill(customerEmail, page);
        return new ResponseEntity<BillDto>(bill, HttpStatus.OK);
    }
}
