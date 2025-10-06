package com.flaco.booking_service.controller;

import com.flaco.booking_service.dto.BookingRequestDTO;
import com.flaco.booking_service.model.Booking;
import com.flaco.booking_service.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    
    @PostMapping
    public Mono<ResponseEntity<Booking>> createBooking(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody BookingRequestDTO bookingRequest) {

        return bookingService.createBooking(userId, bookingRequest)
                .map(booking -> ResponseEntity.status(HttpStatus.CREATED).body(booking));
    }

    
    @GetMapping
    public Mono<ResponseEntity<List<Booking>>> getMyBookings(@RequestHeader("X-User-Id") Long userId) {
        return bookingService.findUserBookings(userId)
                .map(ResponseEntity::ok);
    }
}