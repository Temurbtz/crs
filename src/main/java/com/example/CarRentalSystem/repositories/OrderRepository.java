package com.example.CarRentalSystem.repositories;

import com.example.CarRentalSystem.auth.entities.User;
import com.example.CarRentalSystem.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
