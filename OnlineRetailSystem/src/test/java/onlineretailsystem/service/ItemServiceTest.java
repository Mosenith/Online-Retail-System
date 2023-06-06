package onlineretailsystem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import onlineretailsystem.domain.IndividualItem;
import onlineretailsystem.domain.Item;
import onlineretailsystem.repository.CompositeItemRepository;
import onlineretailsystem.repository.IndividualItemRepository;
import onlineretailsystem.service.ItemService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @Mock
    private IndividualItemRepository individualItemRepository;

    @Mock
    private CompositeItemRepository compositeItemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void getItem_ExistingItem_ReturnsItem() {
        // Arrange
        long itemId = 1L;
        IndividualItem item = new IndividualItem();
        item.setId(itemId);
        item.setName("Item 1");

        when(individualItemRepository.findById(itemId)).thenReturn(Optional.of(item));

        // Act
        Item result = itemService.getItem(itemId);

        // Assert
        assertEquals(item, result);
    }

}