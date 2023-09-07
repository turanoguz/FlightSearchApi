package com.amadeus.flightsearch.exceptions;

public class NoFoundNoArgumentConstructor extends RuntimeException {
    public NoFoundNoArgumentConstructor(String errorMessage) {
        super(errorMessage);
    }
}