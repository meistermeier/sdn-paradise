package org.neo4j.sdnparadise.web;

import org.neo4j.sdnparadise.repository.AddressRepository;
import org.neo4j.sdnparadise.repository.EntityRepository;
import org.neo4j.sdnparadise.repository.OfficerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.stream.Collectors;

@Controller
public class WelcomeController {

	private final OfficerRepository officerRepository;
	private final EntityRepository entityRepository;
	private final AddressRepository addressRepository;

	public WelcomeController(OfficerRepository officerRepository, EntityRepository entityRepository, AddressRepository addressRepository) {
		this.officerRepository = officerRepository;
		this.entityRepository = entityRepository;
		this.addressRepository = addressRepository;
	}

	@ModelAttribute("addressQuery")
	public AddressQuery addressQuery() {
		return new AddressQuery();
	}

	@GetMapping("/")
	public String index(Model model, @RequestParam(name="limit", defaultValue="10") int limit) {
		model.addAttribute("officerCount", officerRepository.count());
		model.addAttribute("entityCount", entityRepository.count());
		model.addAttribute("topAddresses", addressRepository.findTopAddresses(limit));
		model.addAttribute("locations", KnownLocations.LOCATIONS.keySet().stream().sorted().collect(Collectors.toList()));
		return "index";
	}
}
