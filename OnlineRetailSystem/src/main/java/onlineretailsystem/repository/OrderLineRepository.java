package onlineretailsystem.repository;

import onlineretailsystem.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
