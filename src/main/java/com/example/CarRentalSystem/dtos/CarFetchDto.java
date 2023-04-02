package com.example.CarRentalSystem.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class CarFetchDto {
    private Long carId;
    private String registrationNumber;
    private String model;

    private Double velocity;

    private Double pricePerHour;

    private String description;
    private Date dateOfProduction;
}
