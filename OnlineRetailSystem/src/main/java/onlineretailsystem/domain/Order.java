package onlineretailsystem.domain;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Optional;

@Entity
@Table(name = "OrderTable")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private String state;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    private Address shippingAddress;
    @OneToMany
    @JoinColumn(name = "order_id")
    private Collection<OrderLine> orderLines;

    public Order() {

    }

    public Order(String state) {
        this.state = state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<OrderLine> getOrderLines() {
        return orderLines;
    }

    public OrderLine getOrderLineById(Long orderLineId) {
        Optional<OrderLine> orderLineOptional = this.orderLines.stream().filter(ol -> ol.getId() == orderLineId).findFirst();
        return orderLineOptional.orElse(null);
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public void setOrderLine(Long orderLineId, OrderLine orderLine) {
        Optional<OrderLine> orderLineOptional = this.orderLines.stream().filter(ol -> ol.getId() == orderLineId).findFirst();
        orderLineOptional.ifPresent(ol -> ol.clone(orderLine));
    }

    public void parse(Order other) {
        if(other.state != null) this.state = other.state;
        if(other.customer != null) this.customer = other.customer;
        if(other.shippingAddress != null) this.shippingAddress = other.shippingAddress;
        if(other.orderLines != null) this.orderLines = other.orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", orderLines=" + orderLines +
                '}';
    }
}
