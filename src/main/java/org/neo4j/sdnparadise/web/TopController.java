package org.neo4j.sdnparadise.web;

import org.neo4j.sdnparadise.address.AddressRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TopController {

	private final AddressRepository addressRepository;

	public TopController(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@GetMapping("/top/addresses")
	public String index(Model model, @RequestParam(name = "limit", defaultValue = "10") int limit) {
		model.addAttribute("topAddresses", addressRepository.findTopAddresses(limit));
		return "top_addresses";
	}
}
