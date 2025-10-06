package com.flaco.flight_search_service.service;

import com.flaco.flight_search_service.model.Flight;
import com.flaco.flight_search_service.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findFlights(String origin, String destination, LocalDate departureDate) {
        return flightRepository.findFlights(origin, destination, departureDate);
    }

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

   
    public Optional<Flight> findById(Long id) {
        return flightRepository.findById(id);
    }
}