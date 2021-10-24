package com.internship.tailormanager.repository;

import com.internship.tailormanager.enums.OrderStatus;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.Customer;
import com.internship.tailormanager.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order getOrderByOrderIdAndStatus(Long id,Status status);
    Page<Order> findOrderByStatus(Status status, Pageable pageable);
    Page<Order> findOrderByCustomerAndStatusAndOrderStatusAndIsBilled(Customer customer, Status status, OrderStatus orderStatus,boolean isBilled,Pageable pageable);
}
