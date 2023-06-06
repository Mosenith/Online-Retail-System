package onlineretailsystem.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import onlineretailsystem.domain.Address;
import onlineretailsystem.service.dto.CustomerDTO;

import java.util.Collection;

@Data
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private String state;
    private CustomerDTO customer;
    private Address shippingAddress;
    private Collection<OrderLineResponse> orderLines;
}
