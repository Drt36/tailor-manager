package com.internship.tailormanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.tailormanager.dto.StaffDto;
import com.internship.tailormanager.dto.StaffSalaryDto;
import com.internship.tailormanager.service.StaffSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StaffSalaryController {
    @Autowired
    private StaffSalaryService staffSalaryService;

    @PostMapping("/api/user/staffsalary")
    public ResponseEntity<StaffSalaryDto> saveStaffSalary( @RequestParam("staff") String staff,@RequestParam("staffsalary") String staffSalary) throws JsonProcessingException {

        StaffDto staffDto = new ObjectMapper().readValue(staff, StaffDto.class);
        StaffSalaryDto staffSalaryDto = new ObjectMapper().readValue(staffSalary, StaffSalaryDto.class);

        StaffSalaryDto savedStaffSalaryDto = staffSalaryService.saveStaffSalary(staffSalaryDto,staffDto);
        return new ResponseEntity<StaffSalaryDto>(savedStaffSalaryDto, HttpStatus.OK);
    }

    @GetMapping("/api/user/staffsalary/{id}")
    public StaffSalaryDto getStaffSalaryById(@NotNull @PathVariable("id") Long id) {
        return staffSalaryService.getStaffSalaryById(id);
    }

    @GetMapping("/api/user/staffsalary/{staffid}")
    public Page<StaffSalaryDto> getAllByStaffId(@RequestParam("page") int page, @NotNull @PathVariable("email") String staffEmail) {
        Page<StaffSalaryDto> staffSalaryDtos = staffSalaryService.getAllByStaffId(page, staffEmail);
        return staffSalaryDtos;
    }
}
