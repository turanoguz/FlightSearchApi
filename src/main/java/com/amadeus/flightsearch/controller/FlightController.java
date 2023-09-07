package com.amadeus.flightsearch.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.flightsearch.dtos.FlightRequest;
import com.amadeus.flightsearch.dtos.SearchResponse;
import com.amadeus.flightsearch.entities.Airport;
import com.amadeus.flightsearch.entities.Flight;
import com.amadeus.flightsearch.service.FlightService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
	private final FlightService flightService;

	@PostMapping
	public Flight create(@RequestBody FlightRequest request) {
		return flightService.create(request);
	}

	@GetMapping("/{id}")
	public Flight get(@PathVariable Long id) throws NotFoundException {
		return flightService.get(id);
	}

	@GetMapping
	public List<Flight> getAll() {
		return flightService.getAll();
	}

	@PutMapping("/{id}")
	public Flight update(@PathVariable Long id, @RequestBody FlightRequest request) throws NotFoundException {
		return flightService.update(id, request);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		flightService.delete(id);
	}
	
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@GetMapping("/search")
	public SearchResponse findFlights(@RequestParam(name = "departureAirport") Airport departureAirport,
			@RequestParam(name = "arrivalAirport") Airport arrivalAirport,
			@RequestParam(name = "departureDate")  LocalDate departureDate,
			@RequestParam(name = "returnDate", required = false) LocalDate returnDate) {
		return flightService.search(departureAirport, arrivalAirport, departureDate, returnDate);
	}

}
