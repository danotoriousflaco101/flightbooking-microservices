package com.flaco.flight_search_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Data 
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    private String origin; 

    private String destination; 

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private int availableSeats;
}