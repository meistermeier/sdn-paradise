package org.neo4j.sdnparadise.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Entity {

	private Long id;

	private String name;

	@Property(name = "service_provider")
	private String serviceProvider;

	@Relationship(type = "REGISTERED_ADDRESS")
	private Address registeredAddress;

	public String getName() {
		return name;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public Address getRegisteredAddress() {
		return registeredAddress;
	}

	@Override
	public String toString() {
		return getName() + " sponsored by " + getServiceProvider();
	}
}
