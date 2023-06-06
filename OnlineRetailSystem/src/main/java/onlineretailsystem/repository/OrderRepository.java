package onlineretailsystem.repository;

import onlineretailsystem.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByState(String status);
}
