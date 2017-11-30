package org.neo4j.sdnparadise.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class LocationControllerAdvice {

	@ModelAttribute("locations")
	public List<String> locations() {
		return KnownLocations.LOCATIONS.keySet().stream().sorted().collect(Collectors.toList());
	}
}
