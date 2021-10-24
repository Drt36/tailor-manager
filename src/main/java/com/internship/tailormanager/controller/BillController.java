package com.internship.tailormanager.controller;

import com.internship.tailormanager.dto.BillDto;
import com.internship.tailormanager.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("/api/user/bill")
    public Page<BillDto> getAllBills(@RequestParam("page") int page) {
        Page<BillDto> billDtos = billService.getAllBill(page);
        return billDtos;
    }
}
