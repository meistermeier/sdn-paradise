package org.neo4j.sdnparadise.domain;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class TopAddress {

	private String address;

	private long addressId;

	private long count;

	public String getAddress() {
		return address;
	}

	public long getAddressId() {
		return addressId;
	}

	public long getCount() {
		return count;
	}
}
