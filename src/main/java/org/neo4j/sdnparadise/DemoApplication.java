package org.neo4j.sdnparadise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableNeo4jRepositories({
		"org.neo4j.sdnparadise.address",
		"org.neo4j.sdnparadise.entity",
		"org.neo4j.sdnparadise.officer" })
@EntityScan({
		"org.neo4j.sdnparadise.address",
		"org.neo4j.sdnparadise.entity",
		"org.neo4j.sdnparadise.officer" })
@EnableTransactionManagement
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
