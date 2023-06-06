package onlineretailsystem.service;

import onlineretailsystem.domain.CompositeItem;
import onlineretailsystem.domain.IndividualItem;
import onlineretailsystem.domain.Item;
import onlineretailsystem.repository.IndividualItemRepository;
import onlineretailsystem.repository.CompositeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final IndividualItemRepository individualItemRepository;
    private final CompositeItemRepository compositeItemRepository;

    @Autowired
    public ItemService(IndividualItemRepository individualItemRepository, CompositeItemRepository compositeItemRepository) {
        this.individualItemRepository = individualItemRepository;
        this.compositeItemRepository = compositeItemRepository;
    }

    // Inner class for ResourceNotFoundException
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
    
    public List<Item> getAllIndividualItems() {
        List<Item> items = new ArrayList<>();
        items.addAll(individualItemRepository.findAll());
        return items;
    }

    public List<Item> getAllCompositeItems() {
    	List<Item> items = new ArrayList<>();
        items.addAll(compositeItemRepository.findAll());
        return items;
    }


    public Item createItem(Item item) {
        if (item instanceof IndividualItem) {
            return individualItemRepository.save((IndividualItem) item);
        } else if (item instanceof CompositeItem) {
            CompositeItem compositeItem = (CompositeItem) item;
            List<IndividualItem> individualItems = compositeItem.getIndividualItems();
            List<CompositeItem> compositeItems = compositeItem.getCompositeItems();

            // Recursively create and add individual items
            for (int i = 0; i < individualItems.size(); i++) {
                IndividualItem individualItem = individualItems.get(i);
                individualItems.set(i, (IndividualItem) createItem(individualItem));
            }

            // Recursively create and add composite items
            for (int i = 0; i < compositeItems.size(); i++) {
                CompositeItem subCompositeItem = compositeItems.get(i);
                compositeItems.set(i, (CompositeItem) createItem(subCompositeItem));
            }

            return compositeItemRepository.save(compositeItem);
        } else {
            throw new IllegalArgumentException("Invalid item type: " + item.getClass().getSimpleName());
        }
    }



    public Item getItem(Long id) {
        Optional<IndividualItem> optionalIndividualItem = individualItemRepository.findById(id);
        return optionalIndividualItem.orElse(null);
    }
    
    public CompositeItem getCompositeItem(Long id) {
        Optional<CompositeItem> optionalCompositeItem = compositeItemRepository.findById(id);
        return optionalCompositeItem.orElse(null);
    }
    
    public Item updateItem(Long id, Item updatedItem) {
        Item existingItem;

        if (updatedItem instanceof IndividualItem) {
            existingItem = individualItemRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("IndividualItem not found with id: " + id));
        } else if (updatedItem instanceof CompositeItem) {
            existingItem = compositeItemRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("CompositeItem not found with id: " + id));
        } else {
            throw new IllegalArgumentException("Invalid item type: " + updatedItem.getClass().getSimpleName());
        }

        // Update the existing item with the new data
        existingItem.setName(updatedItem.getName());
        existingItem.setBarcode(updatedItem.getBarcode());
        existingItem.setQuantityInStock(updatedItem.getQuantityInStock());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setDescription(updatedItem.getDescription());

        if (existingItem instanceof IndividualItem) {
            return individualItemRepository.save((IndividualItem) existingItem);
        } else {
            return compositeItemRepository.save((CompositeItem) existingItem);
        }
    }

    
    public void deleteItem(Long id) {
        Optional<IndividualItem> optionalIndividualItem = individualItemRepository.findById(id);
        if (optionalIndividualItem.isPresent()) {
            individualItemRepository.delete(optionalIndividualItem.get());
            return;
        }

        throw new ResourceNotFoundException("Item not found with id: " + id);
    }

   
    public void deleteCompositeItem(Long id) {
        Optional<CompositeItem> optionalCompositeItem = compositeItemRepository.findById(id);
        if (optionalCompositeItem.isPresent()) {
            compositeItemRepository.delete(optionalCompositeItem.get());
            return;
        }

        throw new ResourceNotFoundException("Composite item not found with id: " + id);
    }

    public List<Item> searchItems(Long id, String name, String barcode) {
        List<Item> items = new ArrayList<>();

        // Search in IndividualItemRepository
        if (id != null) {
            Optional<IndividualItem> optionalIndividualItem = individualItemRepository.findById(id);
            optionalIndividualItem.ifPresent(items::add);
        } else if (name != null) {
            items.addAll(individualItemRepository.findByNameContainingIgnoreCase(name));
        } else if (barcode != null) {
            items.addAll(individualItemRepository.findByBarcode(barcode));
        }

        // Search in CompositeItemRepository
        if (id != null) {
            Optional<CompositeItem> optionalCompositeItem = compositeItemRepository.findById(id);
            optionalCompositeItem.ifPresent(items::add);
        } else if (name != null) {
            items.addAll(compositeItemRepository.findByNameContainingIgnoreCase(name));
        } else if (barcode != null) {
            items.addAll(compositeItemRepository.findByBarcode(barcode));
        }

        return items;
    }
}
