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
public class OneWayFlightDTO {
	private Airport departureCity;
    private Airport arrivalCity;
    private LocalDate departureDate;
    private double price;
}
