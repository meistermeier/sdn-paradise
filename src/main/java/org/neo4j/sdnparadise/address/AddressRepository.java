package org.neo4j.sdnparadise.address;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends Neo4jRepository<Address, Long> {

	@Query("MATCH (:Entity)-[r:REGISTERED_ADDRESS]->(a:Address) WHERE exists(a.name) RETURN a.name AS address, id(a) AS addressId , count(r) AS count ORDER BY count DESC LIMIT {limit}")
	List<TopAddress> findTopAddresses(@Param("limit") int limit);

	List<Address> findByLocationExistsAndLocationNear(Distance distance, Point point);

}
