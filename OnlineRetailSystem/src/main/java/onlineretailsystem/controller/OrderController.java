package onlineretailsystem.controller;

import onlineretailsystem.contract.OrderResponse;
import onlineretailsystem.domain.Order;
import onlineretailsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/customers/{customerId}/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping()
    public ResponseEntity<Collection<OrderResponse>> getOrders() {
        Collection<OrderResponse> orderResponses = orderService.getAllOrders();
        return new ResponseEntity<Collection<OrderResponse>>(orderResponses, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        if (orderResponse != null) {
            return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addOrder(@PathVariable Long customerId) {
        OrderResponse orderResponse = orderService.addOrder(customerId);
        if (orderResponse != null) {
            return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody Order order) {
        OrderResponse orderResponse = orderService.updateOrder(orderId, order);
        if(orderResponse != null)
            return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{orderId}/nextState")
    public ResponseEntity<OrderResponse> updateOrderState(@PathVariable Long orderId) {
        OrderResponse orderResponse = orderService.updateOrderState(orderId);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        if (orderService.deleteOrder(orderId))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
