package com.example.CarRentalSystem.services;

import com.example.CarRentalSystem.auth.entities.User;
import com.example.CarRentalSystem.dtos.CarFetchDto;
import com.example.CarRentalSystem.dtos.EmbeddedCarDto;
import com.example.CarRentalSystem.dtos.OrderCreateDto;
import com.example.CarRentalSystem.dtos.OrderFetchDto;
import com.example.CarRentalSystem.entities.Car;
import com.example.CarRentalSystem.entities.Order;
import com.example.CarRentalSystem.exceptions.OrderNotFoundException;
import com.example.CarRentalSystem.repositories.CarRepository;
import com.example.CarRentalSystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CarRepository carRepository;

    public List<OrderFetchDto> myOrders(){
        List<OrderFetchDto> myOrders=new ArrayList<>();
        for(Order order:getUser().getOrders()){
             myOrders.add(mapFetch(order));
        }
        return myOrders;
    }

    public OrderFetchDto saveOrder(OrderCreateDto orderCreateDto){
        Car orderCar=carRepository.findById(orderCreateDto.getCarId()).get();
        orderCar.setIsAvailable(false);
        carRepository.save(orderCar);
        return mapFetch(orderRepository.save(Order.builder()
                        .startingDate(orderCreateDto.getStartingDate())
                        .endingDate(orderCreateDto.getEndingDate())
                        .car(orderCar)
                        .customer(getUser())
                .build()));
    }

    public OrderFetchDto getOrder(Long orderId){
        Order order=orderRepository.findById(orderId).get();
        if(order==null & order.getCustomer().getUserId()!=getUser().getUserId()){
            throw new OrderNotFoundException("Order with this id is not found!");
        }
        return mapFetch(order);
    }
    public void deleteOrderById(Long orderId){
        orderRepository.deleteById(orderId);
    }
    private  User getUser(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private OrderFetchDto mapFetch(Order order){
         return OrderFetchDto.builder()
                 .orderId(order.getOrderId())
                 .startingDate(order.getStartingDate())
                 .endingDate(order.getEndingDate())
                 .car(EmbeddedCarDto.builder()
                         .carId(order.getCar().getCarId())
                         .registrationNumber(order.getCar().getRegistrationNumber())
                         .carModel(order.getCar().getModel())
                         .build())
                 .build();
    }

}
