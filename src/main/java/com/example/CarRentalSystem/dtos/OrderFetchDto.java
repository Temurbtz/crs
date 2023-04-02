package com.example.CarRentalSystem.dtos;


import com.example.CarRentalSystem.auth.entities.User;
import com.example.CarRentalSystem.entities.Car;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class OrderFetchDto {
    private Long orderId;

    private Date startingDate;
    private Date endingDate;
    private EmbeddedCarDto car;
}
