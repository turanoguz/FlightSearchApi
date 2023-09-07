package com.amadeus.flightsearch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amadeus.flightsearch.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
