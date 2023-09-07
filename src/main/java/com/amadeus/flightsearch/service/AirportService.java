package com.amadeus.flightsearch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amadeus.flightsearch.core.ReflectionUtils;
import com.amadeus.flightsearch.dtos.AirportRequest;
import com.amadeus.flightsearch.entities.Airport;
import com.amadeus.flightsearch.repository.AirportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirportService {
	private final AirportRepository airportRepository;
	
	public List<Airport> get(){
		return airportRepository.findAll();
	}
	
	public Airport create(AirportRequest request) {
		Airport reflectAirport = ReflectionUtils.cast(request, Airport.class);
		return airportRepository.save(reflectAirport);
	}
	
	public Airport update(Long id, AirportRequest Request) {
		Airport airport = airportRepository.getById(id);
		airport.setCity(Request.getCity());
		return airportRepository.save(airport);
	}
	
	public void delete(Long id) {
		airportRepository.deleteById(id);
	}
}
