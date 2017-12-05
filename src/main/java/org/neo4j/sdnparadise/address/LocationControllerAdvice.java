package org.neo4j.sdnparadise.address;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LocationControllerAdvice {

	@ModelAttribute("locations")
	public List<String> locations() {
		return KnownLocations.LOCATIONS.keySet().stream().sorted().collect(Collectors.toList());
	}
}
