package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.*;
import com.internship.tailormanager.enums.OrderStatus;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.mapper.*;
import com.internship.tailormanager.model.*;
import com.internship.tailormanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
public class OrderService {

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    InventoryMapper inventoryMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private MeasurmentMapper measurmentMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillService billService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private MeasurmentService measurmentService;


    public OrderDto saveOrder(OrderDto orderDto, MeasurmentDto measurmentDto, CustomerDto customer, ProductDto product, StaffDto staffDto) {

        Measurment measurment = measurmentMapper.dtoToModel(measurmentService.saveMeasurment(measurmentDto));

        Order order = orderMapper.dtoToModel(orderDto);
        order.setStatus(Status.ACTIVE);
        order.setDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.NEW);
        order.setTotalAmount((orderDto.getClothAmount() + orderDto.getOrderAmount()) - orderDto.getDiscount());
        order.setRemainingAmount((orderDto.getTotalAmount() - orderDto.getAdvance()));
        order.setIsBilled(false);
        order.setMeasurment(measurment);
        order.setCustomer(customerMapper.dtoToModel(customer));
        order.setProduct(productMapper.dtoToModel(product));
        order.setStaff(staffMapper.dtoToModel(staffDto));

        Inventory inventory = inventoryMapper.dtoToModel(inventoryService.getInventoryById(product.getInventory().getId()));
        inventory.setLength(inventory.getLength() - product.getClothLength());

        OrderDto savedOrderDto = orderMapper.modelToDto(orderRepository.save(order));

        //email the customer


        return savedOrderDto;
    }

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

    public Page<OrderDto> getAllActiveOrdersByCustomer(int page, String email) {
        Pageable pageable = PageRequest.of(page, 2);

        Customer customer = customerMapper.dtoToModel(customerService.getCustomer(email));

        Page<Order> orders = orderRepository.findOrderByCustomerAndStatusAndOrderStatusAndIsBilled(customer, Status.ACTIVE, OrderStatus.NEW, false, pageable);

        Page<OrderDto> orderDtos = orders.map(order -> orderMapper.modelToDto(order));
        return orderDtos;
    }


    public OrderDto updateOrder(Long id, OrderDto orderDto, MeasurmentDto measurmentDto, StaffDto staffDto) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setDeliveryDate(orderDto.getDeliveryDate());
        order.setOrderAmount(orderDto.getOrderAmount());
        order.setClothAmount(orderDto.getClothAmount());
        order.setDiscount(orderDto.getDiscount());
        order.setAdvance(orderDto.getAdvance());
        order.setTotalAmount((orderDto.getClothAmount() + orderDto.getOrderAmount()) - orderDto.getDiscount());
        order.setRemainingAmount((orderDto.getTotalAmount() - orderDto.getAdvance()));
        order.setDescription(orderDto.getDescription());
        order.setStaff(staffMapper.dtoToModel(staffDto));

        Order savedOrder = orderRepository.save(order);

        measurmentService.updateMeasurment(order.getMeasurment().getMeasurmentId(), measurmentDto);

        OrderDto updatedOrderDto = orderMapper.modelToDto(savedOrder);

        return updatedOrderDto;
    }

    public OrderDto deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setStatus(Status.REMOVED);

        Inventory inventory = inventoryMapper.dtoToModel(inventoryService.getInventoryById(order.getProduct().getInventory().getId()));
        inventory.setLength(inventory.getLength() + order.getProduct().getClothLength());

        OrderDto deletedOrderDto = orderMapper.modelToDto(orderRepository.save(order));

        //email the customer

        return deletedOrderDto;
    }

    public OrderDto updateDueAmount(Long id, Float payingAmount) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setRemainingAmount(order.getRemainingAmount() - payingAmount);

        Bill bill = billMapper.dtoToModel(billService.getBillById(order.getBill().getBillId()));
        bill.setBillDueamount(bill.getBillDueamount() - payingAmount);
        billService.updateBill(order.getBill().getBillId(), billMapper.modelToDto(bill));

        OrderDto updatedOrderDto = orderMapper.modelToDto(orderRepository.save(order));

        //email the customer

        return updatedOrderDto;
    }

    public OrderDto updateOrderStatus(Long id, OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setOrderStatus(orderStatus);

        OrderDto updatedOrderDto = orderMapper.modelToDto(orderRepository.save(order));

        //email the customer

        return updatedOrderDto;
    }

    public BillDto addToBill(String email, int page) {

        Pageable pageable = PageRequest.of(page, 2);

        Customer customer = customerMapper.dtoToModel(customerService.getCustomer(email));
        Float billTotalAmount = 0f;
        Float billTotalAdvance = 0f;
        Float billTotalDue = 0f;

        Page<Order> orders = orderRepository.findOrderByCustomerAndStatusAndOrderStatusAndIsBilled(customer, Status.ACTIVE, OrderStatus.NEW, false, pageable);

        for (Order order : orders) {
            billTotalAmount += order.getTotalAmount();
            billTotalAdvance += order.getAdvance();
            billTotalDue += order.getRemainingAmount();
        }

        Bill bill = new Bill();
        bill.setBillActualAmount(billTotalAmount);
        bill.setBillAdvanced(billTotalAdvance);
        bill.setBillDueamount(billTotalDue);

        for (Order order : orders) {
            order.setIsBilled(true);
            order.setBill(bill);
        }

        return billService.saveBill(billMapper.modelToDto(bill));
    }
}
