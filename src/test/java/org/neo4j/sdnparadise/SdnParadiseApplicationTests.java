package org.neo4j.sdnparadise;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.sdnparadise.address.AddressRepository;
import org.neo4j.sdnparadise.address.TopAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SdnParadiseApplicationTests {

	@Autowired
	private AddressRepository addressRepository;

	@Test
	public void findTopAddresses() {
		List<TopAddress> topAddresses = addressRepository.findTopAddresses(10);
		assertEquals(10, topAddresses.size());
		assertTrue(topAddresses.get(0).getCount() > 5000);
	}

}
