package org.neo4j.sdnparadise.address;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddressController {

	private final AddressRepository addressRepository;
	private final AddressResolverService addressResolverService;

	public AddressController(AddressRepository addressRepository, AddressResolverService addressResolverService) {
		this.addressRepository = addressRepository;
		this.addressResolverService = addressResolverService;
	}

	@GetMapping("/address/{id}")
	public String addressDetails(@PathVariable("id") Long id, Model model) {
		Address address = addressRepository.findById(id).get();
		addressResolverService.updateLocationIfNecessary(address);
		model.addAttribute("address", address); // <-- (optional) danger zone...
		return "address_details";
	}

	@GetMapping("/address/city")
	public String addressSearch(@RequestParam("city") final String value, Model model) {
		Point location = KnownLocations.LOCATIONS.get(value);
		if (location == null) {
			return "address_not_found";
		}
		List<Address> addresses = addressRepository.findByLocationExistsAndLocationNear(new Distance(100), location);
		model.addAttribute("location", value);
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
