package org.neo4j.sdnparadise.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.conversion.PointConverter;

import java.util.Set;

@NodeEntity
public class Address {

	private Long id;

	@Convert(PointConverter.class)
	private Point location;

	private double latitude;

	private String address;

	@Relationship(type = "REGISTERED_ADDRESS", direction = "INCOMING")
	private Set<Entity> registeredHere;

	@Property(name = "country_codes")
	private String countryCodes;

	@Property(name = "countries")
	private String countries;

	public Point getLocation() {
		return location;
	}

	public String getAddress() {
		return address;
	}

	public String getCountries() {
		return countries;
	}

	public Set<Entity> getRegisteredHere() {
		return registeredHere;
	}
}
