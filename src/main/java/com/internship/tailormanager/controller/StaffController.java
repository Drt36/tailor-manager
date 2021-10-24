package com.internship.tailormanager.controller;

import com.internship.tailormanager.dto.StaffDto;
import com.internship.tailormanager.service.StaffService;
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
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping("/api/user/staff")
    public ResponseEntity<StaffDto> saveStaff(@Valid @RequestBody StaffDto staffDto){
        StaffDto savedStaffDto = staffService.saveStaff(staffDto);
        return new ResponseEntity<StaffDto>(savedStaffDto, HttpStatus.OK);
    }

    @GetMapping("/api/user/staff/{email}")
    public StaffDto getStaff(@NotNull @PathVariable("email") String email) {
        return staffService.getStaff(email);
    }

    @GetMapping("/api/user/staff")
    public Page<StaffDto> getAllStaffs(@RequestParam("page") int page) {
        Page<StaffDto> staffDtos = staffService.getAllActiveStaffs(page);
        return staffDtos;
    }

    @Transactional
    @PutMapping("/api/user/staff/{id}")
    public ResponseEntity<StaffDto> updateStaff(@NotNull @PathVariable("id") Long id,@RequestBody StaffDto staffDto){
        StaffDto savedStaffDto = staffService.updateStaff(id,staffDto);
        return new ResponseEntity<StaffDto>(savedStaffDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/user/staff/delete/{id}")
    public ResponseEntity<StaffDto> deleteStaff(@NotNull @PathVariable("id") Long id) {
        StaffDto staffDto = staffService.deleteStaff(id);
        return new ResponseEntity<StaffDto>(staffDto, HttpStatus.OK);
    }
}
