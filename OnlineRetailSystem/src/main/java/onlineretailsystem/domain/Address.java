package onlineretailsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @Column(name="AddressID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="AddressType")
    private String addressType;
    @Column(name="AddressLine1")
    private String line1;
    @Column(name="AddressLine2")
    private String line2;
    @Column(name="City")
    private String city;
    @Column(name="StateRegion")
    private String stateRegion;
    @Column(name="CountryRegion")
    private String countryRegion;
    @Column(name="PostalCode")
    private String postalCode;
    @Column(name="PhoneNumber")
    private String phoneNumber;
}
