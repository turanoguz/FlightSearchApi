package com.amadeus.flightsearch.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "departure_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate departureDate;
    
    @Column(name = "return_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate returnDate;
    
    @Column(name = "price")
    private Double price;
    
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;
}