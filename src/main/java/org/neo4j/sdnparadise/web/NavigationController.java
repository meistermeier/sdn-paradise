package org.neo4j.sdnparadise.web;

import org.neo4j.sdnparadise.repository.AddressRepository;
import org.neo4j.sdnparadise.repository.EntityRepository;
import org.neo4j.sdnparadise.repository.OfficerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NavigationController {

	private final OfficerRepository officerRepository;
	private final EntityRepository entityRepository;
	private final AddressRepository addressRepository;

	public NavigationController(OfficerRepository officerRepository, EntityRepository entityRepository, AddressRepository addressRepository) {
		this.officerRepository = officerRepository;
		this.entityRepository = entityRepository;
		this.addressRepository = addressRepository;
	}

	@GetMapping("/")
	public String index(Model model, @RequestParam(name = "limit", defaultValue = "10") int limit) {
		model.addAttribute("officerCount", officerRepository.count());
		model.addAttribute("entityCount", entityRepository.count());
		model.addAttribute("topAddresses", addressRepository.findTopAddresses(limit));
		return "index";
	}

	@GetMapping("/faq")
	public String faq() {
		return "faq";
	}
}
