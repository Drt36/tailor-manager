package com.internship.tailormanager.controller;

import com.internship.tailormanager.dto.*;
import com.internship.tailormanager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping( "/api/user/customer")
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        CustomerDto savedCustomerDto = customerService.saveCustomer(customerDto);
        return new ResponseEntity<CustomerDto>(savedCustomerDto, HttpStatus.OK);
    }

    @GetMapping("/api/user/customer/{email}")
    public CustomerDto getCustomer(@NotNull @PathVariable("email") String email) {
        return customerService.getCustomer(email);
    }

    @GetMapping("/api/user/customer")
    public Page<CustomerDto> getAllCustomers(@RequestParam("page") int page) {
        Page<CustomerDto> customerDtos = customerService.getAllActiveCustomers(page);
        return customerDtos;
    }

    @Transactional
    @PutMapping("/api/user/customer/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@NotNull @PathVariable("id") Long id,@RequestBody CustomerDto customerDto){
        CustomerDto savedCustomerDto=customerService.updateCustomer(id,customerDto);
        return new ResponseEntity<CustomerDto>(savedCustomerDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/user/customer/delete/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@NotNull @PathVariable("id") Long id) {
        CustomerDto customerDto = customerService.deleteCustomer(id);
        return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
    }

}
