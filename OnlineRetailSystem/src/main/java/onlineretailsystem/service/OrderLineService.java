package onlineretailsystem.service;

import onlineretailsystem.contract.OrderLineResponse;
import onlineretailsystem.contract.OrderResponse;
import onlineretailsystem.domain.*;
import onlineretailsystem.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderLineService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    ModelMapper modelMapper;

    public Collection<OrderLineResponse> getAllOrderLines(Long customerId, Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        if(orderResponse != null)
            return orderResponse.getOrderLines();
        else
            return null;
    }

    public OrderLineResponse getOrderLineById(Long orderLineId) {
        Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(orderLineId);
        if(optionalOrderLine.isPresent())
            return modelMapper.map(optionalOrderLine.get(), OrderLineResponse.class);
        else
            return null;
    }

    public OrderLineResponse addOrderLine(Long orderId, Long itemId, OrderLine newOrderLine) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(!optionalOrder.isPresent())
            return null;

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if(!optionalItem.isPresent())
            return null;

        Item item = optionalItem.get();
        newOrderLine.setItem(item);
        orderLineRepository.save(newOrderLine);

        Order order = optionalOrder.get();
        order.addOrderLine(newOrderLine);
        orderRepository.save(order);

        return modelMapper.map(newOrderLine, OrderLineResponse.class);
    }

    public OrderLineResponse updateOrderLine(Long orderId, Long orderLineId, OrderLine orderLine) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderLine(orderLineId, orderLine);
            orderRepository.save(order);

            OrderLine updatedOrderLine = order.getOrderLineById(orderLineId);
            if(updatedOrderLine != null) {
                return modelMapper.map(updatedOrderLine, OrderLineResponse.class);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean deleteOrderLine(Long orderLineId) {
        Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(orderLineId);
        if(optionalOrderLine.isPresent()) {
            orderLineRepository.delete(optionalOrderLine.get());
            return true;
        } else {
            return false;
        }
    }
}
