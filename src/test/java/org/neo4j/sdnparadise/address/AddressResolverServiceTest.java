package org.neo4j.sdnparadise.address;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore // ignored because API hits
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressResolverServiceTest {

	@Autowired
	private AddressResolverService resolverService;

	@Test
	public void updatingAddressesWorks() {
		Address address = new Address();
		address.setName("Clifton House; 75 Fort Street; Grand Cayman KY1-1108; Cayman Islands");
		resolverService.updateLocationIfNecessary(address);

		assertThat(address.getLocation()).isNotNull();
	}

}
