package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.StaffDto;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.mapper.StaffMapper;
import com.internship.tailormanager.model.Staff;
import com.internship.tailormanager.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffMapper staffMapper;

    public StaffDto saveStaff(StaffDto staffDto) {
        Staff staff=staffMapper.dtoToModel(staffDto);
        staff.setStatus(Status.ACTIVE);
        return staffMapper.modelToDto(staffRepository.save(staff));
    }

    public StaffDto getStaff(String email) {
        Staff staff = staffRepository.getStaffByEmailAndStatus(email, Status.ACTIVE);
        return staffMapper.modelToDto(staff);
    }

    public Page<StaffDto> getAllActiveStaffs(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Staff> staffs = staffRepository.findStaffByStatus(Status.ACTIVE, pageable);
        Page<StaffDto> staffDtos = staffs.map(staff -> staffMapper.modelToDto(staff));
        return staffDtos;
    }

    public StaffDto updateStaff(Long id, StaffDto staffDto){
        Staff staff = staffRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        staff.setFirstName(staffDto.getFirstName());
        staff.setLastName(staffDto.getLastName());
        staff.setGender(staffDto.getGender());
        staff.setContact(staffDto.getContact());
        staff.setAge(staffDto.getAge());
        staff.setAddress(staffDto.getAddress());

        Staff savedStaff = staffRepository.save(staff);

        return staffMapper.modelToDto(savedStaff);
    }

    public StaffDto deleteStaff(Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        staff.setStatus(Status.REMOVED);
        return staffMapper.modelToDto(staffRepository.save(staff));
    }
}
