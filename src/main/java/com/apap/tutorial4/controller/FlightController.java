package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.service.PilotService;
import com.apap.tutorial4.service.FlightService;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//FlightController

public class FlightController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private PilotService pilotService;

	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);

		model.addAttribute("flight", flight);
		return "addFlight";
	}

	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}

	@RequestMapping(value = "/flight/view")
	private String view(@RequestParam("flightNumber") String flightNumber, Model model) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("flight", flight);
		model.addAttribute("pilot", flight.getPilot());
		return "view-flight";
	}

	@RequestMapping(value = "/flight/delete")
	public String delete(@RequestParam("flightNumber") String flightNumber, Model model) {
		FlightModel deletedFlight = flightService.deleteFlight(flightNumber);
		model.addAttribute("flight", deletedFlight);
		model.addAttribute("pilot", deletedFlight.getPilot());
		return "delete-flight";
	}

	@RequestMapping(value = "/flight/update")
	public String update(@RequestParam("flightNumber") String flightNumber, @RequestParam("origin") String origin, @RequestParam("destination") String destination, @RequestParam("time") Date time, Model model) {
		FlightModel updatedFlight = flightService.updateFlight(flightNumber, origin, destination, time);
		model.addAttribute("flight", updatedFlight);
		model.addAttribute("pilot", updatedFlight.getPilot());
		return "update-flight";
	}


}
