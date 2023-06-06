package onlineretailsystem.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    @JsonIgnore
    private String billingAddress;

    @JsonIgnore
    private List<String> shippingAddress;

    @JsonIgnore
    private int preferableShippingAddressId;

    @JsonIgnore
    private List<CreditCardDTO> creditCardList = new ArrayList<CreditCardDTO>();

    @JsonIgnore
    private List<String> OrderList;

    public CustomerDTO(String username, String password, String firstName, String lastName,
                       String email, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
