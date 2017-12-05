package org.neo4j.sdnparadise.entity;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EntityRepository extends Neo4jRepository<Entity, Long> {

	List<Entity> findByRegisteredAddressLocationNear(Distance distance, Point point);
}
