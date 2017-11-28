package org.neo4j.sdnparadise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.sdnparadise.domain.TopAddress;
import org.neo4j.sdnparadise.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private AddressRepository addressRepository;

	@Test
	public void findTopAddresses() {
		List<TopAddress> topAddresses = addressRepository.findTopAddresses();
		assertEquals(10, topAddresses.size());
	}

}
