package onlineretailsystem.repository;

import onlineretailsystem.domain.CompositeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompositeItemRepository extends JpaRepository<CompositeItem, Long> {
    List<CompositeItem> findByNameContainingIgnoreCase(String name);
    List<CompositeItem> findByBarcode(String barcode);
    List<CompositeItem> findByIdOrNameContainingIgnoreCaseOrBarcode(Long id, String name, String barcode);
}
