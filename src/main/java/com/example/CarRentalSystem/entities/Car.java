package com.example.CarRentalSystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Column(name = "registration_number", nullable = false, length = 8)
    private String registrationNumber;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "velocity", nullable = false)
    private Double velocity;

    @Column(name="price",nullable = false)
    private Double pricePerHour;

    @Column(name = "description", nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name="date_of_production",nullable = false)
    private Date dateOfProduction;

    @Column(name = "is_available", nullable = false)
    private Boolean  isAvailable=true;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "car_id",
            referencedColumnName = "carId"
    )
    private List<Order> orders;
}
