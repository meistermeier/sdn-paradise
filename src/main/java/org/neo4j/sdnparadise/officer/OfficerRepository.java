package org.neo4j.sdnparadise.officer;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OfficerRepository extends Neo4jRepository<Officer, Long> {

}
