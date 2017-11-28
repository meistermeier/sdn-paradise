package org.neo4j.sdnparadise.repository;

import org.neo4j.sdnparadise.domain.Address;
import org.neo4j.sdnparadise.domain.TopAddress;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface AddressRepository extends Neo4jRepository<Address, Long> {

	@Query("MATCH (:Entity)-[r:REGISTERED_ADDRESS]->(a:Address) WHERE exists(a.latitude) AND exists(a.address) RETURN a.address AS address, id(a) AS addressId , count(r) AS count ORDER BY count DESC LIMIT 10")
	List<TopAddress> findTopAddresses();

	List<Address> findByLatitudeExistsAndLocationNear(Distance distance, Point point);

}
