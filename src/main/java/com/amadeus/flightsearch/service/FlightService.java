package com.amadeus.flightsearch.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.amadeus.flightsearch.core.ReflectionUtils;
import com.amadeus.flightsearch.dtos.FlightRequest;
import com.amadeus.flightsearch.dtos.OneWayFlightDTO;
import com.amadeus.flightsearch.dtos.SearchResponse;
import com.amadeus.flightsearch.entities.Airport;
import com.amadeus.flightsearch.entities.Flight;
import com.amadeus.flightsearch.repository.FlightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightService {
	private final FlightRepository flightRepository;

	public Flight create(FlightRequest request) {
		Flight reflectFlight = ReflectionUtils.cast(request, Flight.class);
		return flightRepository.save(reflectFlight);
	}

	public Flight get(Long id) throws NotFoundException {
		return flightRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	public List<Flight> getAll() {
		return flightRepository.findAll();
	}

	public Flight update(Long id, FlightRequest request) throws NotFoundException {
		Flight existingFlight = flightRepository.findById(id).orElseThrow(NotFoundException::new);
		existingFlight.setDepartureAirport(request.getDepartureAirport());
		existingFlight.setArrivalAirport(request.getArrivalAirport());
		existingFlight.setDepartureDate(request.getDepartureDate());
		existingFlight.setReturnDate(request.getReturnDate());
		existingFlight.setPrice(request.getPrice());
		return flightRepository.save(existingFlight);
	}

	public void delete(Long id) {
		flightRepository.deleteById(id);
	}

	public SearchResponse search(Airport departureAirport, Airport arrivalAirport, LocalDate departureDate,LocalDate returnDate) {
		SearchResponse response = new SearchResponse();
		if (returnDate == null) {
			List<OneWayFlightDTO> dtos = new ArrayList<>();
			List<Flight> flight = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDate(
					departureAirport, arrivalAirport, departureDate);
			for (Flight f : flight) {
				OneWayFlightDTO dto = new OneWayFlightDTO();
				dto.setDepartureCity(f.getDepartureAirport());
				dto.setArrivalCity(f.getArrivalAirport());
				dto.setDepartureDate(f.getDepartureDate());
				dto.setPrice(f.getPrice());
				dtos.add(dto);
			}
			response.setOutboundFlight(dtos);
			return response;
		} else {
			List<OneWayFlightDTO> departureDto = new ArrayList<>();
			List<OneWayFlightDTO> returnDto = new ArrayList<>();
			List<Flight> flight = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDate(
					departureAirport, arrivalAirport, departureDate);
			for (Flight f : flight) {
				OneWayFlightDTO departure = new OneWayFlightDTO();
				departure.setDepartureCity(f.getDepartureAirport());
				departure.setArrivalCity(f.getArrivalAirport());
				departure.setDepartureDate(f.getDepartureDate());
				departure.setPrice(f.getPrice());
				departureDto.add(departure);
			}
			List<Flight> returnFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDate(arrivalAirport, departureAirport, returnDate);
			for (Flight f : returnFlights) {
				OneWayFlightDTO returns = new OneWayFlightDTO();
				returns.setDepartureCity(f.getDepartureAirport());
				returns.setArrivalCity(f.getArrivalAirport());
				returns.setDepartureDate(f.getDepartureDate());
				returns.setPrice(f.getPrice());
				returnDto.add(returns);
			}
			response.setOutboundFlight(departureDto);
			response.setReturnFlight(returnDto);
			return response;
		}
	}
}
