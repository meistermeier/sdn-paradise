package org.neo4j.sdnparadise.repository;

import org.neo4j.sdnparadise.domain.Officer;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OfficerRepository extends Neo4jRepository<Officer, Long> {

}
