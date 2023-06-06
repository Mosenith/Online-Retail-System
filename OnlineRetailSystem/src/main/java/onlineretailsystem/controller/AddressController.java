package onlineretailsystem.controller;

import onlineretailsystem.contract.AddressRequest;
import onlineretailsystem.contract.AddressResponse;
import onlineretailsystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers/{customerId}/addresses")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAddressesByCustomerId(@PathVariable Long customerId){
        AddressResponse response = addressService.getAddressesByCustomerId(customerId);
        if(response == null)
            return new ResponseEntity<String>("Customer with ID: " + customerId + " not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<AddressResponse>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addAddressByCustomerId(@PathVariable Long customerId,
                                                    @RequestBody AddressRequest addressRequest){

        AddressResponse response = addressService.addAddressByCustomerId(customerId, addressRequest);
        if(response == null)
            return new ResponseEntity<String>("Customer with ID: " + customerId + " not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<AddressResponse>(response, HttpStatus.OK);
    }
    @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddressByIds (@PathVariable("customerId") Long customerId,
                                                 @PathVariable("addressId") Long addressId,
                                                 @RequestBody AddressRequest addressRequest){
        AddressResponse response = addressService.updateAddressByIds(customerId, addressId, addressRequest);
        if(response == null)
            return new ResponseEntity<String>("Customer with ID: " + customerId + " not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<AddressResponse>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateDefaultAddressByCustomerId(@PathVariable("customerId") Long customerId,
                                                              @RequestParam("defaultShippingID") Long addressId){
        AddressResponse response = addressService.updateDefaultAddressByCustomerId(customerId,addressId);
        if(response == null)
            return new ResponseEntity<String>("Customer with ID: " + customerId + " not found", HttpStatus.NOT_FOUND);
        else if (response.getDefaultShippingAddressId() != addressId)
            return new ResponseEntity<String>("Customer with ID: " + customerId + " has no shippingAddressID: " + addressId, HttpStatus.NOT_FOUND);
        return new ResponseEntity<AddressResponse>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddressByIds (@PathVariable("customerId") Long customerId,
                                                 @PathVariable("addressId") Long addressId){
        AddressResponse response = addressService.deleteAddressByIds(customerId, addressId);
        if(response == null)
            return new ResponseEntity<String>("Customer with ID: " + customerId + " not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<AddressResponse>(response, HttpStatus.OK);
    }
}
