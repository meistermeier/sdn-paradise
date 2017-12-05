package org.neo4j.sdnparadise.address;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressResolverService {

	private final AddressRepository addressRepository;

	@Value("${google.api-key}")
	private String apiKey;

	public AddressResolverService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public void updateLocationIfNecessary(Address address) {
		if (address.getLocation() != null) {
			return;
		}

		RestTemplate restTemplate = new RestTemplate();
		String jsonResponse = restTemplate.getForObject("https://maps.googleapis.com/maps/api/geocode/json?address=" +
				address.getName() +
				"&key=" + apiKey, String.class);

		Point point = new AddressParser().readLocation(jsonResponse);
		if (point != null) {
			address.setLocation(point);
			addressRepository.save(address);
		}
	}
}
