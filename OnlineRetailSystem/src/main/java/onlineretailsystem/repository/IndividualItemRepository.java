package onlineretailsystem.repository;

import onlineretailsystem.domain.IndividualItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualItemRepository extends JpaRepository<IndividualItem, Long> {
    List<IndividualItem> findByNameContainingIgnoreCase(String name);
    List<IndividualItem> findByBarcode(String barcode);
    List<IndividualItem> findByIdOrNameContainingIgnoreCaseOrBarcode(Long id, String name, String barcode);
}
