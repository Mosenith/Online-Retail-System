package onlineretailsystem.domain;

import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SequenceGenerator(name = "composite_item_seq", sequenceName = "composite_item_seq", allocationSize = 1)
public class CompositeItem extends Item {

    @ManyToMany
    @JoinTable(
            name = "composite_item_component",
            joinColumns = @JoinColumn(name = "composite_item_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<IndividualItem> individualItems = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "composite_item_composite_item",
            joinColumns = @JoinColumn(name = "parent_composite_item_id"),
            inverseJoinColumns = @JoinColumn(name = "child_composite_item_id")
    )
    private List<CompositeItem> compositeItems = new ArrayList<>();
}

