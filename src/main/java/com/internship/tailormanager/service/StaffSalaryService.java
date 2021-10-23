package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.ExpenseDto;
import com.internship.tailormanager.dto.StaffSalaryDto;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.mapper.StaffSalaryMapper;
import com.internship.tailormanager.model.Expense;
import com.internship.tailormanager.model.Staff;
import com.internship.tailormanager.model.StaffSalary;
import com.internship.tailormanager.repository.StaffSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StaffSalaryService {

    @Autowired
    private StaffSalaryRepository staffSalaryRepository;

    @Autowired
    private StaffSalaryMapper staffSalaryMapper;

    public StaffSalaryDto saveStaffSalary(StaffSalaryDto staffSalaryDto) {
        StaffSalary staffSalary=staffSalaryMapper.dtoToModel(staffSalaryDto);

        return staffSalaryMapper.modelToDto(staffSalaryRepository.save(staffSalary));
    }

    public StaffSalaryDto getStaffSalaryById(Long id) {
        StaffSalary staffSalary = staffSalaryRepository.getById(id);
        return staffSalaryMapper.modelToDto(staffSalary);
    }

    public Page<StaffSalaryDto> getAllByStaffId(int page,Long staffId) {
        Pageable pageable = PageRequest.of(page, 2);

        Page<StaffSalary> staffSalaries = staffSalaryRepository.findStaffSalaryByStaffId(staffId,pageable);

        Page<StaffSalaryDto> staffSalaryDtos = staffSalaries.map(staffSalary -> staffSalaryMapper.modelToDto(staffSalary));
        return staffSalaryDtos;
    }
}
