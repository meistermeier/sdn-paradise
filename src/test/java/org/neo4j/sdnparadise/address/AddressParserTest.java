package org.neo4j.sdnparadise.address;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.geo.Point;

public class AddressParserTest {

	private String jsonContent;
	private String corruptedJsonContent;
	private String missingLocationJsonContent;



	@Before
	public void setup() throws URISyntaxException, IOException {
		jsonContent = Files
				.lines(Paths.get(getClass().getResource("/address_json_correct.json").toURI()))
				.reduce("", (firstString, secondString) -> firstString + secondString);
		corruptedJsonContent = Files
				.lines(Paths.get(getClass().getResource("/address_json_corrupted.json").toURI()))
				.reduce("", (firstString, secondString) -> firstString + secondString);
		missingLocationJsonContent = Files
				.lines(Paths.get(getClass().getResource("/address_json_missing_location.json").toURI()))
				.reduce("", (firstString, secondString) -> firstString + secondString);
	}

	@Test
	public void readsCoordinatesCorrect() throws IOException {
		Offset<Double> offset = Offset.offset(0.001);

		Point point = new AddressParser().readLocation(jsonContent);
		assertThat(point.getX()).isEqualTo(37.4224764, offset);
		assertThat(point.getY()).isEqualTo(-122.0842499, offset);
	}

	@Test
	public void returnsNullIfJsonIsCorrupt() throws IOException {
		Point point = new AddressParser().readLocation(corruptedJsonContent);
		assertThat(point).isNull();
	}

	@Test
	public void returnsNullIfJsonHasNoLocation() throws IOException {
		Point point = new AddressParser().readLocation(missingLocationJsonContent);
		assertThat(point).isNull();
	}

}
