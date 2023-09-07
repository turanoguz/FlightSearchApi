package com.amadeus.flightsearch.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amadeus.flightsearch.dtos.AuthenticationRequest;
import com.amadeus.flightsearch.dtos.AuthenticationResponse;
import com.amadeus.flightsearch.dtos.RegisterRequest;
import com.amadeus.flightsearch.entities.User;
import com.amadeus.flightsearch.entities.enums.Role;
import com.amadeus.flightsearch.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder()
		        .email(request.getEmail())
		        .password(passwordEncoder.encode(request.getPassword()))
		        .role(Role.USER)
		        .build();
		    var savedUser = userRepository.save(user);
		    var jwtToken = jwtService.generateToken(user);
		    return AuthenticationResponse.builder()
		        .token(jwtToken)
		        .build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(
		            request.getEmail(),
		            request.getPassword()
		        )
		    );
		    var user = userRepository.findByEmail(request.getEmail())
		        .orElseThrow();
		    var jwtToken = jwtService.generateToken(user);
		    return AuthenticationResponse.builder()
		    	.token(jwtToken)
		        .build();
	}
}
