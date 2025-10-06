package com.flaco.flight_search_service.repository;

import com.flaco.flight_search_service.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

 
    @Query("SELECT f FROM Flight f WHERE f.origin = :origin AND f.destination = :destination AND CAST(f.departureTime AS date) = :departureDate")
    List<Flight> findFlights(@Param("origin") String origin,
                             @Param("destination") String destination,
                             @Param("departureDate") LocalDate departureDate);
}
