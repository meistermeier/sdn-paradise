package org.neo4j.sdnparadise.officer;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.sdnparadise.address.Address;

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
