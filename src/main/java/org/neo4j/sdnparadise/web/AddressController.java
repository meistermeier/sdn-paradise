package org.neo4j.sdnparadise.web;

import org.neo4j.sdnparadise.domain.Address;
import org.neo4j.sdnparadise.repository.AddressRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AddressController {

	private final AddressRepository addressRepository;

	public AddressController(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@GetMapping("/address/{id}")
	public String addressDetails(@PathVariable("id") Long id, Model model) {
		model.addAttribute("address", addressRepository.findById(id).get()); // <-- (optional) danger zone...
		return "address_details";
	}

	@GetMapping("/address/find")
	public String addressSearch(@ModelAttribute final AddressQuery addressQuery, final BindingResult bindingResult, Model model) {
		Point location = KnownLocations.LOCATIONS.get(addressQuery.getValue());
		List<Address> addresses = addressRepository.findByLatitudeExistsAndLocationNear(new Distance(100), location);
		model.addAttribute("location", addressQuery.getValue());
		model.addAttribute("addresses", addresses);
		model.addAttribute("mapCenter", calculateMapCenter(location, addresses));
		return "address_results";
	}

	private Point calculateMapCenter(Point startLocation, List<Address> addresses) {
		if (addresses.isEmpty()) {
			return startLocation;
		}

		double centerLatitude = 0;
		double centerLongitude = 0;
		for (Address address : addresses) {
			centerLatitude += address.getLocation().getX();
			centerLongitude += address.getLocation().getY();
		}

		int addressCount = addresses.size();
		return new Point(centerLatitude / addressCount, centerLongitude / addressCount);
	}
}
