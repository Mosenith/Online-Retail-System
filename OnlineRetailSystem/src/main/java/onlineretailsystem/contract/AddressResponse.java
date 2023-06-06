package onlineretailsystem.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import onlineretailsystem.domain.Address;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class AddressResponse {
    private Long id;
    private Address billingAddress;
    private Collection<Address> shippingAddress = new ArrayList<>();
    private Long defaultShippingAddressId;
}
