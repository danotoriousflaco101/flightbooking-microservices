package com.flaco.booking_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDTO {
    private Long flightId;
    private String seatNumber;
}
