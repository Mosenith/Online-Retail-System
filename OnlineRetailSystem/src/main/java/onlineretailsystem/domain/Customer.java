package onlineretailsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Column(name = "CreditCardList")
    private List<CreditCard> creditCardList = new ArrayList<CreditCard>();

    //--------address---------------------
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="BillingAddressID")
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CustomerID")
    private Collection<Address> shippingAddress = new ArrayList<>();

    @Column(name="DefaultShippingAddressId")
    private Long defaultShippingAddressId;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(String username, String password, String firstName, String lastName, String email,
                    String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public void addCreditCard(CreditCard creditCard) {
        creditCardList.add(creditCard);
    }


    public void addShippingAddress(Address shippingAddress){
        this.shippingAddress.add(shippingAddress);
    }


    public List<Order> getOrders() {
        return this.orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
