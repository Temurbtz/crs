package com.example.CarRentalSystem.controllers;

import com.example.CarRentalSystem.dtos.CarCreateDto;
import com.example.CarRentalSystem.dtos.CarFetchDto;
import com.example.CarRentalSystem.entities.Car;
import com.example.CarRentalSystem.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping("/")
    public List<CarFetchDto> fetchCarList() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarFetchDto> getCarById(@PathVariable("id") Long carId){
        return ResponseEntity.ok(carService.getById(carId));
    }
    @GetMapping("/{model}")
    public ResponseEntity<CarFetchDto> getCarByModel(@PathVariable("model") String model){
        return ResponseEntity.ok(carService.getByModel(model));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<CarFetchDto> saveCar(@RequestBody CarCreateDto carCreateDto){
        return ResponseEntity.ok(carService.saveCar(carCreateDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CarFetchDto> updateCar(@PathVariable("id") Long carId,@RequestBody CarCreateDto carCreateDto){
        return ResponseEntity.ok(carService.updateCar(carId,carCreateDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long carId){
        carService.deleteCarById(carId);
    }


}
