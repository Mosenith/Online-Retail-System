package onlineretailsystem.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressRequest {
    private String addressType;
    private String line1;
    private String line2;
    private String city;
    private String stateRegion;
    private String countryRegion;
    private String postalCode;
    private String phoneNumber;
}
