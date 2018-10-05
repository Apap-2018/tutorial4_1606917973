package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//PilotController

@Controller
public class PilotController {

	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		model.addAttribute("pilotFlight", pilot.getPilotFlight());
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel deletedPilot = pilotService.deletePilot(licenseNumber);
		model.addAttribute("pilot", deletedPilot);
		return "delete-pilot";
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
	public String update(@RequestParam("licenseNumber") String licenseNumber, @RequestParam("name") String name, @RequestParam("flyHour") String flyHour, Model model) {
		PilotModel updatedPilot = pilotService.updatePilot(licenseNumber, name, flyHour);
		model.addAttribute("pilot", updatedPilot);
		return "update-pilot";
	}
	
}
