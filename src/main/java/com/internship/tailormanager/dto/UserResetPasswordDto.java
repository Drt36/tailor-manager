package com.internship.tailormanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResetPasswordDto {

    @NotEmpty(message = "Code should not be empty!")
    @Size(min = 6, max = 6, message = "Code should be 6 digits.")
    private String otpCode;

}
