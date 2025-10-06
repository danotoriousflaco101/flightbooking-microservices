package com.flaco.booking_service.service;

import com.flaco.booking_service.dto.BookingRequestDTO;
import com.flaco.booking_service.dto.FlightDTO;
import com.flaco.booking_service.model.Booking;
import com.flaco.booking_service.model.BookingStatus;
import com.flaco.booking_service.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final WebClient.Builder webClientBuilder;

    public BookingService(BookingRepository bookingRepository, WebClient.Builder webClientBuilder) {
        this.bookingRepository = bookingRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<Booking> createBooking(Long userId, BookingRequestDTO bookingRequest) {
        
        return webClientBuilder.build().get()
                .uri("lb://flight-search-service/api/flights/" + bookingRequest.getFlightId())
                .retrieve()
                .bodyToMono(FlightDTO.class)
                .flatMap(flight -> {
                    if (flight.getAvailableSeats() > 0) {
                    
                        Booking newBooking = new Booking();
                        newBooking.setUserId(userId);
                        newBooking.setFlightId(bookingRequest.getFlightId());
                        newBooking.setSeatNumber(bookingRequest.getSeatNumber());
                        newBooking.setStatus(BookingStatus.CONFIRMED);
                        newBooking.setBookingTime(LocalDateTime.now());
                        return Mono.just(bookingRepository.save(newBooking));
                    } else {
                        return Mono.error(new RuntimeException("Nessun posto disponibile per questo volo."));
                    }
                });
    }

    public Mono<List<Booking>> findUserBookings(Long userId) {
        return Mono.fromCallable(() -> bookingRepository.findByUserId(userId));
    }
}
