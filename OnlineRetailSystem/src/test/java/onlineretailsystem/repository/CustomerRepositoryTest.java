package onlineretailsystem.repository;

import jakarta.persistence.EntityManager;
import onlineretailsystem.repository.CustomerRepository;
import onlineretailsystem.domain.Address;
import onlineretailsystem.domain.Customer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    //------------------testing method for addresses-----------------------------
    private Customer getCustomer(){
        Customer customer = new Customer();

        Address billing = new Address();
        //billing.setId(1L);
        billing.setLine1("Kirkwood");
        billing.setPostalCode("52556");
        billing.setCity("Fairfield");
        billing.setCountryRegion("USA");
        customer.setBillingAddress(billing);

        Address shipping1 = new Address();
        //shipping1.setId(2L);
        shipping1.setLine1("Kirkwood 1");
        shipping1.setPostalCode("52556");
        shipping1.setCity("Fairfield");
        shipping1.setCountryRegion("USA");
        customer.addShippingAddress(shipping1);

        Address shipping2 = new Address();
        //shipping2.setId(3L);
        shipping2.setLine1("Kirkwood 1");
        shipping2.setPostalCode("52556");
        shipping2.setCity("Dallas");
        shipping2.setCountryRegion("USA");
        customer.addShippingAddress(shipping2);

        customer.setDefaultShippingAddressId(3L);

        return customer;
    }
    @Test
    public void whenGetAddressesByCustomerId_thenReturnCustomer(){
        Customer customer = getCustomer();
        testEntityManager.persist(customer);
        testEntityManager.flush();
        Customer found = customerRepository.getAddressesByCustomerId(customer.getId());

        assertThat(found).isEqualTo(customer);
    }
}
