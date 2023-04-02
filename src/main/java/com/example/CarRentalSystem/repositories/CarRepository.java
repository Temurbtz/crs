package com.example.CarRentalSystem.repositories;

import com.example.CarRentalSystem.dtos.CarFetchDto;
import com.example.CarRentalSystem.dtos.CarRequest;
import com.example.CarRentalSystem.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Car findByModel(String model);
}
