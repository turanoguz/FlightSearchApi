package com.amadeus.flightsearch.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amadeus.flightsearch.entities.Airport;
import com.amadeus.flightsearch.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{
	List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDate(Airport departureAirport, Airport arrivalAirport, LocalDate departureDate);
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateAndReturnDate(Airport departureAirport, Airport arrivalAirport, LocalDate departureDate, LocalDate returnDate);
}
