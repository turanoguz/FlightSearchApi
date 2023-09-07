package com.amadeus.flightsearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amadeus.flightsearch.entities.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long>{
	List<Airport> findByCity(String city);
}
