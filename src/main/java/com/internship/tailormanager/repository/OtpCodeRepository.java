package com.internship.tailormanager.repository;

import com.internship.tailormanager.enums.CodeStatus;
import com.internship.tailormanager.model.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpCodeRepository extends JpaRepository<OtpCode,Long> {
    OtpCode getOtpCodeByEmailAndAndCode(String email,String code);

    Boolean existsOtpCodeByEmailAndCode(String email,String code);

    @Modifying
    @Query("update OtpCode o set o.codeStatus=:codeStatus where o.email=:email and o.code=:code")
    void updateOtpCodeByCode(@Param("code") String code, @Param("email") String email, @Param("codeStatus")CodeStatus codeStatus);
}
