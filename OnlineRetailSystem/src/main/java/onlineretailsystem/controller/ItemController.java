package onlineretailsystem.controller;

import onlineretailsystem.domain.CompositeItem;
import onlineretailsystem.domain.IndividualItem;
import onlineretailsystem.domain.Item;
import onlineretailsystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllIndividualItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/composite")
    public ResponseEntity<List<Item>> getAllCompositeItems() {
        List<Item> items = itemService.getAllCompositeItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody IndividualItem item) {
        Item newItem = itemService.createItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }
    
    @PostMapping("/composite")
    public ResponseEntity<Item> createCompositeItem(@RequestBody CompositeItem item) {
        Item newItem = itemService.createItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        Item item = itemService.getItem(id);

        if (item instanceof IndividualItem) {
            return ResponseEntity.ok(item);
        } else {
            String errorMessage = "No page found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/composite/{id}")
    public ResponseEntity<?> getCompositeItem(@PathVariable Long id) {
        Item item = itemService.getCompositeItem(id);

        if (item instanceof CompositeItem) {
            return ResponseEntity.ok(item);
        } else {
            String errorMessage = "No page found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody IndividualItem item) {
        Item updatedItem = itemService.updateItem(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }
    
    @PutMapping("/composite/{id}")
    public ResponseEntity<Item> updateCompositeItem(@PathVariable Long id, @RequestBody CompositeItem item) {
        Item updatedItem = itemService.updateItem(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/composite/{id}")
    public ResponseEntity<String> deleteCompositeItem(@PathVariable Long id) {
        itemService.deleteCompositeItem(id);
        return new ResponseEntity<>("Composite item deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchItems(@RequestParam(value = "id", required = false) Long id,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "barcode", required = false) String barcode) {
        List<Item> items = itemService.searchItems(id, name, barcode);

        if (items == null || items.isEmpty()) {
            String errorMessage = "No items found with the specified criteria";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } else {
            return ResponseEntity.ok(items);
        }
    }
}
