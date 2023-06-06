package onlineretailsystem.repository;

import onlineretailsystem.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    @Query("select cc from CreditCard cc where cc.customer.id = :id")
    List<CreditCard> findByCustomerId(@Param("id") long customerId);

    @Modifying
    @Query("delete from CreditCard cc where cc.id = :id")
    void deleteById(@Param("id") long creditCardId);
}
