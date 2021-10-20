package com.internship.tailormanager.dto;

import com.internship.tailormanager.enums.CodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpCodeGetDto {
    private Long otpCodeId;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private CodeStatus codeStatus;
    private String email;
}
