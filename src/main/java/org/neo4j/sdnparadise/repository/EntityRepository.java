package org.neo4j.sdnparadise.repository;

import org.neo4j.sdnparadise.domain.Entity;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface EntityRepository extends Neo4jRepository<Entity, Long> {

	List<Entity> findByRegisteredAddressLocationNear(Distance distance, Point point);
}
