package onlineretailsystem.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import onlineretailsystem.domain.IndividualItem;

@Data
@NoArgsConstructor
public class OrderLineResponse {
    private Long id;
    private Integer qualtity;
    private Double discount;
    private IndividualItem item;
}
