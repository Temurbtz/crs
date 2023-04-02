package com.example.CarRentalSystem.entities;

import com.example.CarRentalSystem.auth.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Temporal(TemporalType.DATE)
    @Column(name="starting_date",nullable = false)
    private Date startingDate;

    @Temporal(TemporalType.DATE)
    @Column(name="ending_date",nullable = false)
    private Date endingDate;@ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "userId"
    )
    private User customer;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "car_id",
            referencedColumnName = "carId"
    )
    private Car car;

}
