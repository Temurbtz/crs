package com.example.CarRentalSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class OrderCreateDto {
    private Date startingDate;
    private Date endingDate;
    private Long carId;
}
