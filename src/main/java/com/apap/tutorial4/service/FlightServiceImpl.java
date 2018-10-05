package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDb;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//FlightServiceImpl

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	@Override
	public FlightModel deleteFlight(String flightNumber) {
		FlightModel deleted = flightDb.findByFlightNumber(flightNumber);
		flightDb.delete(flightDb.findByFlightNumber(flightNumber));
		return deleted;
	}

	@Override
	public FlightModel updateFlight(String flightNumber, String origin, String destination, Date time) {
		flightDb.findByFlightNumber(flightNumber).setOrigin(origin);
		flightDb.findByFlightNumber(flightNumber).setDestination(destination);
		flightDb.findByFlightNumber(flightNumber).setTime(time);
		FlightModel updated = flightDb.findByFlightNumber(flightNumber);
		return updated;
	}

	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}
	
}
