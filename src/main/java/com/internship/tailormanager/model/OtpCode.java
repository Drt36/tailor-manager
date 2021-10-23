package com.internship.tailormanager.model;

import com.internship.tailormanager.enums.CodeStatus;
import com.internship.tailormanager.listener.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "otpcode", schema = "tms")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(EntityListener.class)
public class OtpCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otpCodeId;

    @Column(nullable = false, length = 6)
    private String code;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 4)
    private CodeStatus codeStatus;

    @Column(nullable = false, length = 50)
    private String email;
}
