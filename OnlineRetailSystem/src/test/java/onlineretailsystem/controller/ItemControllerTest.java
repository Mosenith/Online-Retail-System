package onlineretailsystem.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import onlineretailsystem.controller.ItemController;
import onlineretailsystem.domain.IndividualItem;
import onlineretailsystem.domain.Item;
import onlineretailsystem.service.ItemService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {
    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @Test
    void getAllItems_ReturnsListOfItems() {
        // Arrange
        IndividualItem item1 = new IndividualItem();
        item1.setId(1L);
        item1.setName("Item 1");

        IndividualItem item2 = new IndividualItem();
        item2.setId(2L);
        item2.setName("Item 2");

        List<Item> items = Arrays.asList(item1, item2);
        when(itemService.getAllIndividualItems()).thenReturn(items);

        // Act
        ResponseEntity<List<Item>> response = itemController.getAllItems();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

}
