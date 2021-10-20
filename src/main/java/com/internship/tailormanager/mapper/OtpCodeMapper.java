package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.OtpCodeGetDto;
import com.internship.tailormanager.model.OtpCode;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OtpCodeMapper {
    OtpCodeMapper OTP_CODE_MAPPER= Mappers.getMapper(OtpCodeMapper.class);

    OtpCodeGetDto modelToOtpCodeGetDto(OtpCode otpCode);

    @InheritInverseConfiguration
    OtpCode dtoToOtpCodeModel(OtpCodeGetDto otpCodeGetDto);

}
