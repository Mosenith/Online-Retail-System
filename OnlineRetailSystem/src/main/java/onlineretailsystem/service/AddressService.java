package onlineretailsystem.service;

import jakarta.transaction.Transactional;
import onlineretailsystem.contract.AddressRequest;
import onlineretailsystem.contract.AddressResponse;
import onlineretailsystem.domain.Address;
import onlineretailsystem.domain.Customer;
import onlineretailsystem.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class AddressService {
    private static final String ADDRESSTYPE_SHIPPING = "shipping";
    private static final String ADDRESSTYPE_BILLING = "billing";
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelMapper;

    //--------------create
    public AddressResponse addAddressByCustomerId(Long customerId, AddressRequest addressRequest){
        Customer customer = customerRepository.getAddressesByCustomerId(customerId);
        if(customer == null) return null;
        Address address = modelMapper.map(addressRequest, Address.class);
        if(addressRequest.getAddressType().equals(ADDRESSTYPE_SHIPPING))
            customer.addShippingAddress(address);
        else if(addressRequest.getAddressType().equals(ADDRESSTYPE_BILLING)){
            if(customer.getBillingAddress() == null)
                customer.setBillingAddress(address);
            else
                modelMapper.map(addressRequest, customer.getBillingAddress());
        } else return modelMapper.map(customer, AddressResponse.class);

        customerRepository.save(customer);

        return modelMapper.map(customer, AddressResponse.class);
    }

    //---------------------read All
    public AddressResponse getAddressesByCustomerId(Long customerId){
        Customer customer = customerRepository.getAddressesByCustomerId(customerId);
        if(customer == null) return null;
        AddressResponse addressResponse = modelMapper.map(customer, AddressResponse.class);
        return addressResponse;
    }

    //---------------------update
    public AddressResponse updateAddressByIds(Long customerId, Long addressId, AddressRequest addressRequest){
        Customer customer = customerRepository.getAddressesByCustomerId(customerId);
        if(customer == null) return null;
        Address address = modelMapper.map(addressRequest, Address.class);
        if(addressRequest.getAddressType().equals(ADDRESSTYPE_SHIPPING)){
            for(Address a: customer.getShippingAddress()){
                if(a.getId() == addressId)
                    modelMapper.map(addressRequest, a);
            }
        } else if(addressRequest.getAddressType().equals(ADDRESSTYPE_BILLING))
            modelMapper.map(addressRequest, customer.getBillingAddress());
        else  return modelMapper.map(customer, AddressResponse.class);;

        customerRepository.save(customer);
        return modelMapper.map(customer, AddressResponse.class);
    }
    public AddressResponse updateDefaultAddressByCustomerId(Long customerId, Long newAddressId){
        Customer customer = customerRepository.getAddressesByCustomerId(customerId);
        if(customer == null) return null;
        customer.getShippingAddress().forEach(s ->{
            if(s.getId() == newAddressId) customer.setDefaultShippingAddressId(newAddressId);
        });

        customerRepository.save(customer);
        return modelMapper.map(customer, AddressResponse.class);
    }

    //-----------------delete
    private Address getAddressByAddressId(Collection<Address> addresses, Long addressId){
        for(Address a: addresses){
            if(a.getId().equals(addressId))
                return a;
        }
        return null;
    }
    public AddressResponse deleteAddressByIds(Long customerId, Long addressId){
        Customer customer = customerRepository.getAddressesByCustomerId(customerId);
        if(customer == null) return null;
        if(customer.getBillingAddress() != null && customer.getBillingAddress().getId() == addressId){
            customer.setBillingAddress(null);
        } else {
            Address address = getAddressByAddressId(customer.getShippingAddress(), addressId);
            if(address != null) customer.getShippingAddress().remove(address);
            else return modelMapper.map(customer,AddressResponse.class);
        }
        customerRepository.save(customer);
        return modelMapper.map(customer,AddressResponse.class);
    }
}
