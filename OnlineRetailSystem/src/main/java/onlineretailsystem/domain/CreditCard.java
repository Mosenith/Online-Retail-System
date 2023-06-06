package onlineretailsystem.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "CreditCard")
@Data
@NoArgsConstructor
public class CreditCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CardNumber")
    private String cardNumber;

    @Column(name = "CVVNumber")
    private Integer cvv;

    @Column(name = "ExpirationDate")
    private LocalDate expirationDate;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Customer_CreditCard", joinColumns = @JoinColumn(
            name = "CreditCardID", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", nullable = true))
    private Customer customer;

    public CreditCard(String cardNumber, Integer cvv, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    public void setCustomerId(long customerId) {
        this.customer.setId(customerId);
    }

}
