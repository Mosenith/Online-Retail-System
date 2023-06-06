package onlineretailsystem.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import onlineretailsystem.domain.CreditCard;
import onlineretailsystem.domain.Customer;
import onlineretailsystem.service.CustomerService;
import onlineretailsystem.service.dto.CreditCardDTO;
import onlineretailsystem.service.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    ModelMapper mapper;

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") long customerId) throws JsonProcessingException {
        CustomerDTO foundCustomer = customerService.getCustomer(customerId);

        if(foundCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<CustomerDTO>(foundCustomer, HttpStatus.OK);
    }

    @GetMapping ("/customers")
    public ResponseEntity<?> getAllCustomers() {
        List<CustomerDTO> customerList = customerService.getAllCustomer();
        return new ResponseEntity<List<CustomerDTO>>(customerList, HttpStatus.OK);
    }

    @PostMapping("/customer/create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        CustomerDTO createdCustomer = customerService.createCustomer(customer);

        return new ResponseEntity<CustomerDTO>(createdCustomer, HttpStatus.OK);
    }

    @PutMapping("/customer/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long customerId,
                                            @RequestBody Customer customer) {
        if(customerService.getCustomer(customerId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CustomerDTO updateCustomer = customerService.updateCustomer(customerId, customer);

        return new ResponseEntity<CustomerDTO>(updateCustomer, HttpStatus.OK);
    }


    // Credit Card
    @PostMapping("/customer/{id}/add-creditcard")
    public ResponseEntity<?> addCreditCard(@PathVariable("id") long customerId,
                                           @RequestBody CreditCard creditCard) {

        if(customerService.getCustomer(customerId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customerService.addNewCreditCard(customerId, creditCard);
        CustomerDTO searchedCustomer = customerService.getCustomer(customerId);

        return new ResponseEntity<CustomerDTO>(searchedCustomer, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}/creditcards")
    public ResponseEntity<?> getCreditCards(@PathVariable("id") long customerId) {
        if(customerService.getCustomer(customerId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CreditCardDTO> creditCardDTOList = customerService.getCustomerCreditCards(customerId);
        return new ResponseEntity<List<CreditCardDTO>>(creditCardDTOList, HttpStatus.OK);
    }

    @PutMapping("/customer/{id}/update-creditcard/{creditcardId}")
    public ResponseEntity<?> updateCreditCard(@PathVariable("id") long customerId,
                                              @PathVariable("creditcardId") long creditCardId,
                                              @RequestBody CreditCard creditCard) {
        if(customerService.getCustomer(customerId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CreditCardDTO updatedCreditCard = customerService.updateCreditCard(customerId, creditCardId, creditCard);

        return new ResponseEntity<CreditCardDTO>(updatedCreditCard, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}/delete-creditcard/{creditcardId}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable("id") long customerId,
                                              @PathVariable("creditcardId") long creditCardId) {
        if(customerService.getCustomer(customerId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CreditCardDTO> creditCardDTOList = customerService.deleteCreditCard(customerId, creditCardId);

        return new ResponseEntity<List<CreditCardDTO>>(creditCardDTOList, HttpStatus.OK);
    }
}
