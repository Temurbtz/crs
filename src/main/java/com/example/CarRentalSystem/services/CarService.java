package com.example.CarRentalSystem.services;

import com.example.CarRentalSystem.dtos.CarCreateDto;
import com.example.CarRentalSystem.dtos.CarFetchDto;
import com.example.CarRentalSystem.dtos.CarRequest;
import com.example.CarRentalSystem.entities.Car;
import com.example.CarRentalSystem.exceptions.CarNotFoundException;
import com.example.CarRentalSystem.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService{
    @Autowired
    private CarRepository carRepository;

    public List<CarFetchDto> getAllCars() {
        List<CarFetchDto> cars=new ArrayList<CarFetchDto>();
        for (Car car:carRepository.findAll()) {
            cars.add(mapFetch(car));
        }
        return cars;
    }

    public CarFetchDto getById(Long carId){
        Car car=carRepository.findById(carId).get();
        if(car==null){
            throw new CarNotFoundException("Car with this is is not found!");
        }
        return mapFetch(car);
    }

    public CarFetchDto getByModel(String model){
        Car car=carRepository.findByModel(model);
        if(car==null){
            throw new CarNotFoundException("Car with this model is not found! ");
        }
        return mapFetch(car);
    }

    public CarFetchDto saveCar(CarCreateDto carCreateDto){
        return mapFetch(carRepository.save(Car.builder()
                .registrationNumber(carCreateDto.getRegistrationNumber())
                .model(carCreateDto.getModel())
                .velocity(carCreateDto.getVelocity())
                .pricePerHour(carCreateDto.getPricePerHour())
                .description(carCreateDto.getDescription())
                .dateOfProduction(carCreateDto.getDateOfProduction())
                .build()));
    }

    public void deleteCarById(Long carId){
       carRepository.deleteById(carId);
    }
    public CarFetchDto updateCar(Long carId,CarCreateDto carCreateDto){
       Car car=carRepository.findById(carId).get();
       if(car==null){
           throw new CarNotFoundException("Car with this id is not found!");
       }
       car.setRegistrationNumber(carCreateDto.getRegistrationNumber());
       car.setModel(carCreateDto.getModel());
       car.setVelocity(carCreateDto.getVelocity());
       car.setPricePerHour(carCreateDto.getPricePerHour());
       car.setDescription(carCreateDto.getDescription());
       car.setDateOfProduction(carCreateDto.getDateOfProduction());
       return mapFetch(carRepository.save(car));
    }
    private CarFetchDto mapFetch(Car car){
        return  CarFetchDto.builder()
                .carId(car.getCarId())
                .registrationNumber(car.getRegistrationNumber())
                .model(car.getModel())
                .velocity(car.getVelocity())
                .pricePerHour(car.getPricePerHour())
                .description(car.getDescription())
                .dateOfProduction(car.getDateOfProduction())
                .build();
    }
}
