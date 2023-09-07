package com.amadeus.flightsearch.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
	private List<OneWayFlightDTO> outboundFlight;
	private List<OneWayFlightDTO> returnFlight;
		
	
}
