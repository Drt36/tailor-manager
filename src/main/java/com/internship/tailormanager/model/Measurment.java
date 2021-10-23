package com.internship.tailormanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.internship.tailormanager.listener.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "measurment", schema = "tms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(EntityListener.class)
public class Measurment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurmentId;
    private Float length;
    private Float waist;
    private Float shoulder;
    private Float hand;
    private Float neck;
    private Float chest;
    private Float thigh;
    private Float innerLenght;
    private Float hip;

    @OneToOne(mappedBy = "measurment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "backrefrence-measurment")
    private Order order;
}
