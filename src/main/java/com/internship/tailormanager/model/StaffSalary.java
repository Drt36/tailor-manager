package com.internship.tailormanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.internship.tailormanager.listener.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "staffsalary", schema = "tms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(EntityListener.class)
public class StaffSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Float amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    @JsonBackReference(value = "backrefrence-staffsalary")
    private Staff staff;
}
