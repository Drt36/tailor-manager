package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.OtpCodeGetDto;
import com.internship.tailormanager.enums.CodeStatus;
import com.internship.tailormanager.mapper.OtpCodeMapper;
import com.internship.tailormanager.model.OtpCode;
import com.internship.tailormanager.repository.OtpCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OtpCodeService {
    @Autowired
    private OtpService otpService;

    @Autowired
    private  UserService userService;

    @Autowired
    private OtpCodeRepository otpCodeRepository;

    @Autowired
    private OtpCodeMapper otpCodeMapper;

    @Autowired
    private EmailService emailService;

    public OtpCodeGetDto saveOtpCode(String email) {
        LocalDateTime localDateTime = LocalDateTime.now();
        OtpCode otpCodeNew = new OtpCode();
        otpCodeNew.setCode(otpService.generateOtpCode());
        otpCodeNew.setCreatedAt(localDateTime);
        otpCodeNew.setExpiredAt(localDateTime.plusMinutes(2));
        otpCodeNew.setCodeStatus(CodeStatus.NEW);
        otpCodeNew.setEmail(email);
        OtpCode otpCode = otpCodeRepository.save(otpCodeNew);
        emailService.sendEmail(email, "OTP code : " + otpCode.getCode() + " Do not share this code with others." +
                " It will expire in 2 minutes.", "Reset Password Code.");
        return otpCodeMapper.modelToOtpCodeGetDto(otpCode);
    }

    public String validateOtpCode(String email, String code) {
        boolean result = false;
        String response = null;
        OtpCode otpCode = otpCodeRepository.getOtpCodeByEmailAndAndCode(email, code);
        if (otpCode.getCodeStatus() == CodeStatus.NEW) {
            if (otpCode.getExpiredAt().isAfter(LocalDateTime.now())) {
                result = otpCodeRepository.existsOtpCodeByEmailAndCode(email, code);
            } else {
                return "Otp code has been Expired.";
            }
        } else {
            return "Otp code has been used.";
        }

        if (result) {
            otpCodeRepository.updateOtpCodeByCode(code, email, CodeStatus.USED);
            userService.resetUserPassword(email);
            response="Password has been reseted Successfully.Please check your email.";
        }
        else {
            response="Failed to reset Password.";
        }
        return response;
    }

}
