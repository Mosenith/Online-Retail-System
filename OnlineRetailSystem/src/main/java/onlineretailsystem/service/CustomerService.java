package onlineretailsystem.service;

import onlineretailsystem.domain.CreditCard;
import onlineretailsystem.domain.Customer;
import onlineretailsystem.repository.CreditCardRepository;
import onlineretailsystem.repository.CustomerRepository;
import onlineretailsystem.service.dto.CreditCardDTO;
import onlineretailsystem.service.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    ModelMapper mapper;

    public CustomerDTO getCustomer(long customerId) {
        return mapper.map(customerRepository.findById(customerId).get(), CustomerDTO.class);
    }

    public CustomerDTO createCustomer(Customer customer) {
        customerRepository.save(customer);
        return mapper.map(customerRepository.findById(customer.getId()).get(), CustomerDTO.class);
    }

    public List<CustomerDTO> getAllCustomer() {
        return customerRepository.findAll().stream().map(
                entity -> mapper.map(entity, CustomerDTO.class)
        ).collect(Collectors.toList());
    }

    public CustomerDTO addNewCreditCard(long customerID, CreditCard creditCard) {
        Customer searchCustomer = customerRepository.findById(customerID).get();

        // add crediCard to Customer
        searchCustomer.addCreditCard(creditCard);

        // set creditCard properties
        creditCard.setCustomer(searchCustomer);

        creditCard.setCustomerId(customerID);
        creditCardRepository.save(creditCard);
        customerRepository.save(searchCustomer);
        creditCardRepository.flush();

        return mapper.map(searchCustomer, CustomerDTO.class);
    }

    public CustomerDTO updateCustomer(long customerId, Customer updatedCustomer) {
        Customer existedCustomer = customerRepository.findById(customerId).get();

        existedCustomer.setUsername(updatedCustomer.getUsername());
        existedCustomer.setPassword(updatedCustomer.getPassword());
        existedCustomer.setFirstName(updatedCustomer.getFirstName());
        existedCustomer.setLastName(updatedCustomer.getLastName());
        existedCustomer.setEmail(updatedCustomer.getEmail());
        existedCustomer.setRole(updatedCustomer.getRole());

        customerRepository.save(existedCustomer);

        return mapper.map(existedCustomer, CustomerDTO.class);
    }

    public List<CreditCardDTO> getCustomerCreditCards(long customerId) {
        List<CreditCard> creditCardList = creditCardRepository.findByCustomerId(customerId);

        return creditCardList.stream().map(
                entity -> mapper.map(entity, CreditCardDTO.class))
                .collect(Collectors.toList());
    }

    public CreditCardDTO updateCreditCard(long customerId, long creditCardId, CreditCard creditCard) {
        Customer existedCustomer = customerRepository.findById(customerId).get();

        // Find the credit card in the list of credit cards
        CreditCard existedCreditCard = existedCustomer.getCreditCardList().stream()
                .filter(c -> c.getId() == creditCardId)
                .findFirst()
                .orElseThrow(null);

        // Update the credit card
        existedCreditCard.setCardNumber(creditCard.getCardNumber());
        existedCreditCard.setCvv(creditCard.getCvv());
        existedCreditCard.setExpirationDate(creditCard.getExpirationDate());

        // Save the customer
        creditCardRepository.save(existedCreditCard);
        customerRepository.save(existedCustomer);

        return mapper.map(existedCreditCard, CreditCardDTO.class);
    }

    public List<CreditCardDTO> deleteCreditCard(long customerId, long creditCardId) {
        Customer existedCustomer = customerRepository.findById(customerId).get();

        // Verify the credit card existed in the list of credit cards
        CreditCard searchedCreditCard = existedCustomer.getCreditCardList().stream()
                .filter(c -> c.getId() == creditCardId)
                .findFirst()
                .orElseThrow(null);

        // Remove From Repository
        creditCardRepository.deleteById(creditCardId);

        return getCustomerCreditCards(customerId);
    }

}
