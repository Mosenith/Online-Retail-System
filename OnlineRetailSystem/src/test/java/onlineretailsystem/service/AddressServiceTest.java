package onlineretailsystem.service;

import onlineretailsystem.contract.AddressRequest;
import onlineretailsystem.contract.AddressResponse;
import onlineretailsystem.domain.Address;
import onlineretailsystem.domain.Customer;
import onlineretailsystem.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @TestConfiguration
    static class CustomerServiceImplTestContextConfiguration {
        @Bean
        public AddressService accountService() {
            return new AddressService();
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    @Autowired
    private AddressService addressService;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelMapper = new ModelMapper();

    @Before
    public void setup(){
        Customer customer = getCustomer();

        Mockito.when(customerRepository.getAddressesByCustomerId(customer.getId()))
                .thenReturn(customer);
    }

    private Customer getCustomer(){
        Customer customer = new Customer();
        customer.setId(1L);
        Address billing = new Address();
        billing.setId(1L);
        billing.setLine1("Kirkwood");
        billing.setPostalCode("52556");
        billing.setCity("Fairfield");
        billing.setCountryRegion("USA");
        billing.setAddressType("billing");
        customer.setBillingAddress(billing);

        Address shipping1 = new Address();
        shipping1.setAddressType("shipping");
        shipping1.setId(2L);
        shipping1.setLine1("Kirkwood 1");
        shipping1.setPostalCode("52556");
        shipping1.setCity("Fairfield");
        shipping1.setCountryRegion("USA");
        customer.addShippingAddress(shipping1);

        Address shipping2 = new Address();
        shipping2.setAddressType("shipping");
        shipping2.setId(3L);
        shipping2.setLine1("Kirkwood 1");
        shipping2.setPostalCode("52556");
        shipping2.setCity("Dallas");
        shipping2.setCountryRegion("USA");
        customer.addShippingAddress(shipping2);

        customer.setDefaultShippingAddressId(3L);
        return  customer;
    }
    @Test
    public void whenAddAddressByCustomerId_thenAdd(){
        AddressRequest shipping = new AddressRequest();
        shipping.setAddressType("shipping");
        shipping.setLine1("Kirkwood 1");
        shipping.setPostalCode("52556");
        shipping.setCity("Dallas");
        shipping.setCountryRegion("USA");
        AddressResponse response=  addressService.addAddressByCustomerId(1L, shipping);
        Customer customer = customerRepository.getAddressesByCustomerId(1L);

        verify(customerRepository, times(1)).save(customer);
        assertThat(response).isEqualTo(modelMapper.map(customer,AddressResponse.class));
    }

    @Test
    public void whenGetAddressesByCustomerId_thenReturnAddressResponse(){
        AddressResponse response = addressService.getAddressesByCustomerId(1L);
        Customer customer = getCustomer();
        assertThat(response).isEqualTo(modelMapper.map(customer,AddressResponse.class));
    }

    @Test
    public void whenUpdateAddressByIds_thenUpdate(){
        AddressRequest shipping = new AddressRequest();
        shipping.setAddressType("shipping");
        shipping.setLine1("Kirkwood 1");
        shipping.setPostalCode("52556");
        shipping.setCity("Dallas");
        shipping.setCountryRegion("USA");

        AddressResponse response = addressService.updateAddressByIds(1L, 1L, shipping);
        Customer customer = customerRepository.getAddressesByCustomerId(1L);

        verify(customerRepository, times(1)).save(customer);
        assertThat(response).isEqualTo(modelMapper.map(customer,AddressResponse.class));
    }

    @Test
    public void whenUpdateDefaultAddressByCustomerId_thenUpdate(){
        AddressResponse response = addressService.updateDefaultAddressByCustomerId(1L, 2L);
        Customer customer = customerRepository.getAddressesByCustomerId(1L);

        verify(customerRepository, times(1)).save(customer);
        assertThat(response).isEqualTo(modelMapper.map(customer,AddressResponse.class));
    }

    @Test
    public void whenDeleteAddressByIds_thenDelete(){
        AddressResponse response = addressService.deleteAddressByIds(1L, 2L);
        Customer customer = customerRepository.getAddressesByCustomerId(1L);

        verify(customerRepository, times(1)).save(customer);
        assertThat(response).isEqualTo(modelMapper.map(customer,AddressResponse.class));
    }
}
