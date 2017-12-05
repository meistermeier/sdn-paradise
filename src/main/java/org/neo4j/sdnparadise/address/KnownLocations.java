package org.neo4j.sdnparadise.address;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.geo.Point;

class KnownLocations {

	// sometimes it is easier to solve things "simple" in the first run ;)
	static final Map<String, Point> LOCATIONS = new HashMap<>();

	static {
		if (LOCATIONS.size() == 0) {
			try {
				URI uri = KnownLocations.class.getResource("/data/locations.csv").toURI();
				List<String> rows = Files.readAllLines(Paths.get(uri));
				for (String row : rows) {
					String[] data = row.split(";");
					LOCATIONS.put(data[0], new Point(Double.parseDouble(data[1]), Double.parseDouble(data[2])));
				}
			} catch (URISyntaxException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
