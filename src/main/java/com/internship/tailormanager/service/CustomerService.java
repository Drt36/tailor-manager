package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.CustomerDto;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.mapper.CustomerMapper;
import com.internship.tailormanager.model.Customer;
import com.internship.tailormanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer=customerMapper.dtoToModel(customerDto);
        customer.setStatus(Status.ACTIVE);
        return customerMapper.modelToDto(customerRepository.save(customer));
    }

    public CustomerDto getCustomer(String email) {
        Customer customer = customerRepository.getCustomerByEmailAndStatus(email, Status.ACTIVE);
        return customerMapper.modelToDto(customer);
    }

    public Page<CustomerDto> getAllActiveCustomers(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Customer> customers = customerRepository.findCustomerByStatus(Status.ACTIVE, pageable);

        Page<CustomerDto> customerDtos = customers.map(customer -> customerMapper.modelToDto(customer));
        return customerDtos;
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto){
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setGender(customerDto.getGender());
        customer.setContact(customerDto.getContact());
        customer.setAge(customerDto.getAge());
        customer.setAddress(customerDto.getAddress());

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.modelToDto(savedCustomer);
    }

    public CustomerDto deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        customer.setStatus(Status.REMOVED);
        return customerMapper.modelToDto(customerRepository.save(customer));
    }
}
