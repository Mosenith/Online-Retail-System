package onlineretailsystem.domain;

import lombok.Data;

import java.util.List;

import jakarta.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String name;
    protected String description;
    protected Double price;
    protected String barcode;
    protected int quantityInStock;

    @OneToMany(mappedBy = "item")
    protected List<Review> reviews; 

}
