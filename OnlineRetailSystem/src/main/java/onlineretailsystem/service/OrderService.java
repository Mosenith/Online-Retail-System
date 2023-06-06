package onlineretailsystem.service;

import onlineretailsystem.contract.OrderResponse;
import onlineretailsystem.domain.*;
import onlineretailsystem.repository.CustomerRepository;
import onlineretailsystem.repository.OrderLineRepository;
import onlineretailsystem.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private static final String ORDER_STATE_NEW = "NEW";
    private static final String ORDER_STATE_PLACED = "PLACED";
    private static final String ORDER_STATE_PROCESSED = "PROCESSED";
    private static final String ORDER_STATE_SHIPPED = "SHIPPED";
    private static final String ORDER_STATE_DELIVERED = "DELIVERED";
    private static final String ORDER_STATE_RETURNED = "RETURNED";

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderLineRepository orderLineRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelMapper;

    public OrderResponse updateOrderState(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            String currentState = order.getState();
            order.setState(nextState(currentState));

            orderRepository.save(order);
            return modelMapper.map(order, OrderResponse.class);
        } else {
            return null;
        }
    }

    public String nextState(String currentState) {
        switch (currentState) {
            case ORDER_STATE_NEW:
                return ORDER_STATE_PLACED;
            case ORDER_STATE_PLACED:
                return ORDER_STATE_PROCESSED;
            case ORDER_STATE_PROCESSED:
                return ORDER_STATE_SHIPPED;
            case ORDER_STATE_SHIPPED:
                return ORDER_STATE_DELIVERED;
            case ORDER_STATE_DELIVERED:
                return ORDER_STATE_RETURNED;
            default:
                return currentState;
        }
    }

    public Collection<OrderResponse> getAllOrders() {
        Collection<Order> orders = orderRepository.findAll();

        Collection<OrderResponse> orderResponses = orders.stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toCollection(ArrayList::new));

        return orderResponses;
    }

    public OrderResponse getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent())
            return modelMapper.map(optionalOrder.get(), OrderResponse.class);
        else
            return null;
    }

    public OrderResponse addOrder(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            Order order = new Order(ORDER_STATE_NEW);
            order.setCustomer(customer);

            Collection<Address> shippingAddresses = customer.getShippingAddress();
            if(shippingAddresses.size() > 0) {
                List<Address> shippingAddressList = (List<Address>) shippingAddresses;
                Long defaultShippingAddressId = customer.getDefaultShippingAddressId();

                Optional<Address> optionalAddress = shippingAddressList.stream().filter(address -> address.getId() == defaultShippingAddressId).findFirst();
                Address address = optionalAddress.orElse(shippingAddressList.get(0));

                order.setShippingAddress(address);
            }

            orderRepository.save(order);

            customer.addOrder(order);
            customerRepository.save(customer);

            return modelMapper.map(order, OrderResponse.class);
        } else {
            return null;
        }
    }

    public OrderResponse updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if(order.getState().equals(ORDER_STATE_NEW)) {
                order.parse(updatedOrder);
                orderRepository.save(order);
                return modelMapper.map(order, OrderResponse.class);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean deleteOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            orderRepository.delete(optionalOrder.get());
            return true;
        } else {
            return false;
        }
    }
}
