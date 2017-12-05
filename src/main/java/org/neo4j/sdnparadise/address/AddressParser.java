package org.neo4j.sdnparadise.address;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.geo.Point;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressParser {

	public Point readLocation(String json) {
		try {
			JsonParser parser = new JsonFactory().createParser(json);
			Map values = new ObjectMapper().readValue(parser, Map.class);
			LinkedHashMap firstResult = ((LinkedHashMap) ((ArrayList) values.get("results")).get(0));
			LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) firstResult.get("geometry")).get("location");
			Double latitude = (Double) location.get("lat");
			Double longitude = (Double) location.get("lng");
			return new Point(latitude, longitude);
		} catch (Exception e) {
			return null;
		}
	}

}
