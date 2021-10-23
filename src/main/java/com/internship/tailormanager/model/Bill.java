package com.internship.tailormanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.internship.tailormanager.listener.EntityListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bill", schema = "tms")
@Data
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column(nullable = false)
    private LocalDateTime date;

    private float billActualAmount;

    private float billAdvanced;

    private float billDueamount;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "backrefrence-bill")
    private List<Order> orderList;

}
