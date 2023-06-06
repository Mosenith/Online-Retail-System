package onlineretailsystem.repository;

import onlineretailsystem.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);


    //---------------address---------------------------
    @Query("select distinct c from Customer c left join fetch c.billingAddress left join fetch c.shippingAddress where c.id = :customerId")
    public Customer getAddressesByCustomerId(Long customerId);
}
