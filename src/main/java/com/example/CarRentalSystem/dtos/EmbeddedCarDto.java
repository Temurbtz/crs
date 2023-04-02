package com.example.CarRentalSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmbeddedCarDto {
    private Long carId;
    private String registrationNumber;
    private String carModel;
}
