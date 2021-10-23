package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.BillDto;
import com.internship.tailormanager.mapper.BillMapper;
import com.internship.tailormanager.model.Bill;
import com.internship.tailormanager.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper billMapper;

    public BillDto saveBill(BillDto billDto) {
        return billMapper.modelToDto(billRepository.save(billMapper.dtoToModel(billDto)));
    }

    public BillDto getBillById(Long id) {
        Bill bill = billRepository.getById(id);
        return billMapper.modelToDto(bill);
    }

    public Page<BillDto> getAllBill(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Bill> bills = billRepository.findAll(pageable);

        Page<BillDto> billDtos = bills.map(bill -> billMapper.modelToDto(bill));
        return billDtos;
    }

    public BillDto updateBill(Long id, BillDto billDto){
        Bill bill = billRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        bill.setBillDueamount(billDto.getBillDueamount());

        Bill savedBill = billRepository.save(bill);

        return billMapper.modelToDto(savedBill);
    }
}
