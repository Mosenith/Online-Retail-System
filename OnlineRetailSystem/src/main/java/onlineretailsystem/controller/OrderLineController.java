package onlineretailsystem.controller;

import onlineretailsystem.contract.OrderLineResponse;
import onlineretailsystem.domain.OrderLine;
import onlineretailsystem.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/customers/{customerId}/orders/{orderId}/orderlines")
public class OrderLineController {
    @Autowired
    OrderLineService orderLineService;

    @GetMapping()
    public ResponseEntity<Collection<OrderLineResponse>> getOrderLines(@PathVariable Long customerId, @PathVariable Long orderId) {
        Collection<OrderLineResponse> orderLines = orderLineService.getAllOrderLines(customerId, orderId);
        return new ResponseEntity<Collection<OrderLineResponse>>(orderLines, HttpStatus.OK);
    }

    @GetMapping("/{orderLineId}")
    public ResponseEntity<?> getOrderLineById(@PathVariable Long customerId, @PathVariable Long orderId, @PathVariable Long orderLineId) {
        OrderLineResponse orderLineResponse = orderLineService.getOrderLineById(orderLineId);
        if (orderLineResponse != null) {
            return new ResponseEntity<OrderLineResponse>(orderLineResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<OrderLineResponse> addOrderLine(@PathVariable Long customerId, @PathVariable Long orderId,
                                                          @RequestBody OrderLine newOrderLine, @RequestParam Long itemId) {
        OrderLineResponse orderLineResponse = orderLineService.addOrderLine(orderId, itemId, newOrderLine);
        if (orderLineResponse != null)
            return new ResponseEntity<OrderLineResponse>(orderLineResponse, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{orderLineId}")
    public ResponseEntity<OrderLineResponse> updateOrderLine(@PathVariable Long customerId, @PathVariable Long orderId, @PathVariable Long orderLineId, @RequestBody OrderLine orderLine) {
        OrderLineResponse updatedOrderLine = orderLineService.updateOrderLine(orderId, orderLineId, orderLine);
        if (updatedOrderLine != null)
            return new ResponseEntity<OrderLineResponse>(updatedOrderLine, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{orderLineId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long customerId, @PathVariable Long orderId, @PathVariable Long orderLineId) {
        if(orderLineService.deleteOrderLine(orderLineId))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
