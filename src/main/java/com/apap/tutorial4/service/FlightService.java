package com.apap.tutorial4.service;

import java.sql.Date;
import com.apap.tutorial4.model.FlightModel;

//FlightService

public interface FlightService {
	void addFlight(FlightModel flight);
	FlightModel deleteFlight(String flightNumber);
	FlightModel updateFlight(String flightNumber, String origin, String destination, Date time);
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
}
