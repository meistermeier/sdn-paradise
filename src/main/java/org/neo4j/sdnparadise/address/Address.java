package org.neo4j.sdnparadise.address;

import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.sdnparadise.entity.Entity;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.conversion.PointConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NodeEntity
public class Address {

	private Long id;

	@Convert(PointConverter.class)
	private Point location;

	// just needed for existence check on entity
	private double latitude;

	private String address;

	@JsonIgnore // ignore for thymeleaf javascript inlining
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
