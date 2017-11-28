package org.neo4j.sdnparadise.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Officer {

	private Long id;

	private String name;

	@Relationship(type = "REGISTERED_ADDRESS")
	private Address registeredAddress;

	public String getName() {
		return name;
	}

	public Address getRegisteredAddress() {
		return registeredAddress;
	}

	@Override
	public String toString() {
		return getName();
	}
}
