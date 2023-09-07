package com.amadeus.flightsearch.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.amadeus.flightsearch.entities.Airport;
import com.amadeus.flightsearch.entities.Flight;
import com.amadeus.flightsearch.repository.FlightRepository;

import lombok.RequiredArgsConstructor;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledService {
	private final FlightRepository flightRepository;

	//her gün saat 00:00'da 
	@Scheduled(cron = "0 0 0 * * ?")
	public void loadFlightData() {
		List<Flight> flights = mockFlightData();
		flightRepository.saveAll(flights);
	}

	private List<Flight> mockFlightData() {
		List<Flight> flights = new ArrayList<>();
		List<String> airports = Arrays.asList(
				"İstanbul",
				"Ankara",
				"Kayseri",
				"Trabzon",
				"İzmir",
				"Rize",
				"Adana",
				"Sivas",
				"Adıyaman",
				"Mersin"
				);
        for (int i = 0; i < 10; i++) {
            Flight flight = new Flight();
            flight.setDepartureAirport(new Airport(null,airports.get(i)));
            flight.setArrivalAirport(new Airport(null,airports.get(i+1)));
            flight.setDepartureDate(LocalDate.now().plusDays(i));
            flight.setReturnDate(null);
            flight.setPrice(Math.random() * 1000);
            flights.add(flight);
        }
        return flights;
	}
}
