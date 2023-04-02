package com.example.CarRentalSystem.controllers;


import com.example.CarRentalSystem.dtos.CarCreateDto;
import com.example.CarRentalSystem.dtos.CarFetchDto;
import com.example.CarRentalSystem.dtos.OrderCreateDto;
import com.example.CarRentalSystem.dtos.OrderFetchDto;
import com.example.CarRentalSystem.services.CarService;
import com.example.CarRentalSystem.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/")
    public ResponseEntity<List<OrderFetchDto>> fetchCarList() {
        return ResponseEntity.ok(orderService.myOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderFetchDto> getOrderById(@PathVariable("id") Long orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }


    @PostMapping("/")
    public ResponseEntity<OrderFetchDto> saveOrder(@RequestBody OrderCreateDto orderCreateDto){
        return ResponseEntity.ok(orderService.saveOrder(orderCreateDto));
    }

    @DeleteMapping("/{id}")
    public void cancelOrder(@PathVariable("id") Long orderId){
        orderService.deleteOrderById(orderId);
    }

}
