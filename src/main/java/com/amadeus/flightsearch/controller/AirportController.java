package com.amadeus.flightsearch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.flightsearch.dtos.AirportRequest;
import com.amadeus.flightsearch.entities.Airport;
import com.amadeus.flightsearch.service.AirportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {
	private final AirportService airportService;
	
	@GetMapping
	public ResponseEntity<List<Airport>> get(){
		return ResponseEntity.ok(airportService.get());
	}
	
	@PostMapping
	public ResponseEntity<Airport> create(@RequestBody AirportRequest request){
		return ResponseEntity.ok(airportService.create(request));
	}
	
	@PutMapping
	public ResponseEntity<Airport> update(@RequestParam(name = "id") Long id, AirportRequest request){
		return ResponseEntity.ok(airportService.update(id, request));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		airportService.delete(id);
	}
}
