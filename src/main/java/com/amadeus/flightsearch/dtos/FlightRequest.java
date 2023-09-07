package com.amadeus.flightsearch.dtos;

import java.time.LocalDate;


import com.amadeus.flightsearch.entities.Airport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequest {
	private LocalDate departureDate;
    private LocalDate returnDate;
    private Double price;
    private Airport departureAirport;
    private Airport arrivalAirport;

}
