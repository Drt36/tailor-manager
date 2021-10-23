package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.ExpenseDto;
import com.internship.tailormanager.dto.MeasurmentDto;
import com.internship.tailormanager.dto.StaffSalaryDto;
import com.internship.tailormanager.mapper.MeasurmentMapper;
import com.internship.tailormanager.mapper.StaffSalaryMapper;
import com.internship.tailormanager.model.Expense;
import com.internship.tailormanager.model.Measurment;
import com.internship.tailormanager.model.Order;
import com.internship.tailormanager.model.StaffSalary;
import com.internship.tailormanager.repository.MeasurmentRepository;
import com.internship.tailormanager.repository.StaffSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class MeasurmentService {
    @Autowired
    private MeasurmentRepository measurmentRepository;

    @Autowired
    private MeasurmentMapper measurmentMapper;

    public MeasurmentDto saveMeasurment(MeasurmentDto measurmentDto) {
        Measurment measurment=measurmentMapper.dtoToModel(measurmentDto);

        return measurmentMapper.modelToDto(measurmentRepository.save(measurment));
    }

    public MeasurmentDto getById(Long id) {
        Measurment measurment = measurmentRepository.getById(id);
        return measurmentMapper.modelToDto(measurment);
    }

    public MeasurmentDto updateMeasurment(Long id, MeasurmentDto measurmentDto){
        Measurment measurment = measurmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        measurment.setLength(measurmentDto.getLength());
        measurment.setWaist(measurmentDto.getWaist());
        measurment.setShoulder(measurmentDto.getShoulder());
        measurment.setHand(measurmentDto.getHand());
        measurment.setNeck(measurmentDto.getNeck());
        measurment.setChest(measurmentDto.getChest());
        measurment.setThigh(measurmentDto.getThigh());
        measurment.setInnerLenght(measurmentDto.getInnerLenght());
        measurment.setHip(measurmentDto.getHip());

        Measurment savedMeasurment = measurmentRepository.save(measurment);

        return measurmentMapper.modelToDto(savedMeasurment);
    }

}
