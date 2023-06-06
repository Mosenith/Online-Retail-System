package onlineretailsystem.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreditCardDTO {
    private Long id;

    private String cardNumber;

    private Integer cvv;

    private LocalDate expirationDate;

    @JsonIgnore
    private CustomerDTO customerDTO;

    public CreditCardDTO(String cardNumber, Integer cvv, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }
}
